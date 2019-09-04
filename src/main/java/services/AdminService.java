
package services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.Administrator;
import domain.Conference;
import domain.Reviewer;
import domain.Submission;
import forms.DashboardForm;
import repositories.AdministratorRepository;
import security.UserAccountService;

@Service
@Transactional
public class AdminService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private AdministratorRepository	adminRepository;

	// Supporting services ----------------------------------------------------

	@Autowired
	private UserAccountService		userAccountService;
	@Autowired
	private ActorService			actorService;
	@Autowired
	private ConferenceService		conferenceService;
	@Autowired
	private ReviewerService			reviewerService;
	@Autowired
	private SubmissionService		submissionService;


	public Administrator findPrincipal() {
		this.actorService.assertPrincipalAuthority("ADMIN");
		return (Administrator) this.actorService.findPrincipal();
	}

	public Administrator save(final Administrator admin) {
		// if it's saved for the first time (created), assign a proper make given his name
		final Administrator res = this.adminRepository.save(admin);
		return res;

	}

	public DashboardForm getDashboardStats() {
		final DashboardForm form = new DashboardForm();
		final List<Long> feeStats = this.submissionPerConferenceStats();
		form.setMinConferenceFee(feeStats.get(0));
		form.setMaxConferenceFee(feeStats.get(1));
		form.setStdConferenceFee(feeStats.get(2));

		final List<Long> subStats = this.submissionPerConferenceStats();
		form.setMinSubmissionsPerConference(subStats.get(0));
		form.setMaxSubmissionsPerConference(subStats.get(1));
		form.setStdSubmissionsPerConference(subStats.get(2));

		final List<Long> regStats = this.registrationsPerConferenceStats();
		form.setMinRegistrationsPerConference(regStats.get(0));
		form.setMaxRegistrationsPerConference(regStats.get(1));
		form.setStdRegistrationsPerConference(regStats.get(2));

		return form;
	}

	private List<Long> feePerConferenceStats() {
		final List<Long> res = new ArrayList<Long>();
		final List<Long> perConference = this.adminRepository.feePerConference();
		Integer sum = 0;
		Integer min = null;
		Integer max = null;
		for (Integer i = 0; i < perConference.size(); i++) {
			sum += perConference.get(i).intValue();
			if (min == null || perConference.get(i).intValue() < min)
				min = perConference.get(i).intValue();
			if (max == null || perConference.get(i).intValue() > max)
				max = perConference.get(i).intValue();
		}
		final Long average = (sum.longValue() / Integer.valueOf(perConference.size()).longValue());
		Double deviation = 0.0;
		for (Integer i = 0; i < perConference.size(); i++)
			deviation += deviation + Math.pow(perConference.get(i).longValue() - average, 2);

		res.add((long) min.doubleValue());
		res.add((long) max.doubleValue());
		res.add(Math.round(deviation));
		return res;
	}

	private List<Long> submissionPerConferenceStats() {
		final List<Long> res = new ArrayList<Long>();
		final List<Long> perConference = this.adminRepository.submissionsPerConference();
		Integer sum = 0;
		Integer min = null;
		Integer max = null;
		for (Integer i = 0; i < perConference.size(); i++) {
			sum += perConference.get(i).intValue();
			if (min == null || perConference.get(i).intValue() < min)
				min = perConference.get(i).intValue();
			if (max == null || perConference.get(i).intValue() > max)
				max = perConference.get(i).intValue();
		}
		final Long average = (sum.longValue() / Integer.valueOf(perConference.size()).longValue());
		Double deviation = 0.0;
		for (Integer i = 0; i < perConference.size(); i++)
			deviation += deviation + Math.pow(perConference.get(i).longValue() - average, 2);

		res.add((long) min.doubleValue());
		res.add((long) max.doubleValue());
		res.add(Math.round(deviation));
		return res;
	}

	private List<Long> registrationsPerConferenceStats() {
		final List<Long> res = new ArrayList<Long>();
		final List<Long> perConference = this.adminRepository.registrationsPerConference();
		Integer sum = 0;
		Integer min = null;
		Integer max = null;
		for (Integer i = 0; i < perConference.size(); i++) {
			sum += perConference.get(i).intValue();
			if (min == null || perConference.get(i).intValue() < min)
				min = perConference.get(i).intValue();
			if (max == null || perConference.get(i).intValue() > max)
				max = perConference.get(i).intValue();
		}
		final Long average = (sum.longValue() / Integer.valueOf(perConference.size()).longValue());
		Double deviation = 0.0;
		for (Integer i = 0; i < perConference.size(); i++)
			deviation += deviation + Math.pow(perConference.get(i).longValue() - average, 2);

		res.add((long) min.doubleValue());
		res.add((long) max.doubleValue());
		res.add(Math.round(deviation));
		return res;
	}

	public void autoAssign() {
		this.findPrincipal();
		final List<Reviewer> reviewers = this.reviewerService.findAll();

		final List<Conference> conferences = this.conferenceService.findAll();
		for (final Conference conference : conferences) {
			//for each conference
			final List<String> conferenceKeywords = new ArrayList<>(Arrays.asList(conference.getTitle().split("[, ]+")));
			conferenceKeywords.addAll(Arrays.asList(conference.getSummary().split("[, ]+")));

			final List<Submission> submissions = this.submissionService.findByConference(conference);
			if (submissions != null)
				//if there are submissions for the conference
				for (final Reviewer reviewer : reviewers) {
					//check available reviewers
					List<String> reviewerKeywords = new ArrayList<>();
					reviewerKeywords = Arrays.asList(reviewer.getExpertise().split("[, ]+"));
					for (final String s : reviewerKeywords)
						if (conferenceKeywords.contains(s)) {
							//reviewer selected for conference
							for (final Submission submission : submissions)
								//for each submission
								if (submission.getReviewers().size() < 4 && !submission.getReviewers().contains(reviewer)) {
									//has 3 or less reviewers assigned
									final List<Reviewer> rs = submission.getReviewers();
									rs.add(reviewer);
									submission.setReviewers(rs);
									this.submissionService.save(submission);
								}
							break;
						}
				}
		}
	}

}
