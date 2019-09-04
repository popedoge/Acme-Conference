
package services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Activity;
import domain.Actor;
import domain.Conference;
import domain.Registration;
import domain.Report;
import domain.Submission;
import forms.ConferenceOptionForm;
import repositories.ConferenceRepository;
import security.Authority;

@Service
@Transactional
public class ConferenceService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private ConferenceRepository	conferenceRepository;

	// Supporting services ----------------------------------------------------

	@Autowired
	private ActorService			actorService;
	@Autowired
	private SubmissionService		submissionService;
	@Autowired
	private ReportService			reportService;
	@Autowired
	private ActivityService			activityService;
	@Autowired
	private RegistrationService		registrationService;


	public Conference lock(final Integer id) {
		final Conference conference = this.findById(id);
		conference.setLocked(true);
		return this.save(conference);
	}

	public List<Conference> findFinal() {
		return this.conferenceRepository.findFinal();
	}
	public Conference findById(final int id) {
		return this.conferenceRepository.findOne(id);
	}

	public Conference findByPrincipal() {
		final int actorID = this.actorService.findPrincipal().getId();
		final Conference res = this.conferenceRepository.findOne(actorID);
		return res;
	}

	public Conference save(final Conference conference) {
		if (conference.getActivities() == null)
			conference.setActivities(new ArrayList<Activity>());
		return this.conferenceRepository.save(conference);
	}

	public List<Conference> findAll() {
		return this.conferenceRepository.findAll();
	}

	public Conference create() {
		final Actor actor = this.actorService.findPrincipal();
		Assert.isTrue(actor.getUser().checkAuthority(Authority.ADMIN));
		final Conference res = new Conference();
		res.setLocked(false);
		res.setOwner(actor);
		return res;
	}

	public ConferenceOptionForm options(final Conference conference) {
		final Actor actor = this.actorService.findPrincipal();
		final ConferenceOptionForm res = new ConferenceOptionForm();
		if (actor.getUser().checkAuthority(Authority.ADMIN)) {
			//evaluate if after submissionDL && final
			if (conference.getSubmissionDL().before(new Date()) && conference.getLocked())
				res.setEvaluate(true);
			else
				res.setEvaluate(false);
			//add activity if before event start date
			if (conference.getStartDate().after(new Date()))
				res.setAddActivity(true);
			else
				res.setAddActivity(false);
			res.setMsgReg(true);
			res.setMsgSub(true);
			res.setReg(false);
			res.setSub(false);
		} else if (actor.getUser().checkAuthority(Authority.AUTHOR)) {
			res.setEvaluate(false);
			res.setMsgReg(false);
			res.setMsgSub(false);
			if (conference.getSubmissionDL().before(new Date()))
				res.setSub(false);
			else
				res.setSub(true);
			if (conference.getStartDate().before(new Date()))
				res.setReg(false);
			else
				res.setReg(true);
		} else {
			res.setEvaluate(false);
			res.setMsgReg(false);
			res.setMsgSub(false);
			res.setReg(false);
			res.setSub(false);
		}
		return res;
	}

	public void evaluate(final Conference conference) {
		Assert.isTrue(conference.getSubmissionDL().before(new Date()));
		final List<Submission> submissions = this.submissionService.findByConference(conference);
		for (final Submission sub : submissions) {
			final List<Report> reports = this.reportService.findBySubmission(sub.getId());
			Integer score = this.score(reports, false);
			//score
			if (score == 0)
				score = this.score(reports, true);

			//eval
			if (score >= 0)
				sub.setStatus(Submission.ACCEPTED);
			else
				sub.setStatus(Submission.REJECTED);
			this.submissionService.save(sub);
		}
	}

	private Integer score(final List<Report> reports, final boolean countBorder) {
		Integer res = 0;
		for (final Report rep : reports) {
			if (rep.getDecision().equals(Report.ACCEPT))
				res++;
			else if (rep.getDecision().equals(Report.REJECT))
				res--;
			if (countBorder)
				if (rep.getDecision().equals(Report.BORDER))
					res++;
		}
		return res;
	}

	public void delete(final int id) {
		final List<Activity> activities = this.activityService.findByConference(id);
		final List<Registration> registrations = this.registrationService.findByConference(id);
		final List<Submission> submissions = this.submissionService.findByConference(this.findById(id));
		this.activityService.deleteAll(activities);
		this.registrationService.deleteAll(registrations);
		this.submissionService.deleteAll(submissions);
		this.conferenceRepository.delete(id);

	}

}
