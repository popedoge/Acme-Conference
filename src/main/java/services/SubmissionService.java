
package services;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.Actor;
import domain.Conference;
import domain.Reviewer;
import domain.Submission;
import forms.SubmissionForm;
import repositories.SubmissionRepository;

@Service
@Transactional
public class SubmissionService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private SubmissionRepository	submissionRepo;

	// Supporting services ----------------------------------------------------

	@Autowired
	private ActorService			actorService;
	@Autowired
	private PaperService			paperService;
	@Autowired
	private ConferenceService		conferenceService;

	private final String			dict	= "QWERTYUIOPASDFGHJKLZXCVBNM0123456789";


	public void deleteAll(final List<Submission> submissions) {
		this.submissionRepo.delete(submissions);
	}

	public Submission assignReviewer(final Submission submission, final Reviewer reviewer) {
		final List<Reviewer> reviewers = submission.getReviewers();
		if (!reviewers.contains(reviewer))
			reviewers.add(reviewer);
		submission.setReviewers(reviewers);
		return this.save(submission);
	}

	public List<Submission> findAll() {
		return this.submissionRepo.findAll();
	}

	public Submission create() {
		return new Submission();
	}

	public List<Submission> findByReviewer(final Reviewer reviewer) {
		return this.submissionRepo.findByReviewer(reviewer.getId());
	}

	public List<Submission> findByReviewerAndConference(final Reviewer reviewer, final Conference conference) {
		return this.submissionRepo.findByReviewerAndConference(reviewer.getId(), conference.getId());
	}

	public SubmissionForm formatForm(final Submission sub) {
		final SubmissionForm res = new SubmissionForm();
		res.setOwner(sub.getOwner().getId());
		res.setTicker(sub.getTicker());
		res.setStatus(sub.getStatus());
		res.setConferece(sub.getConference().getId());
		if (sub.getPaper() != null) {
			res.setPaperId(sub.getPaper().getId());
			res.setPaperTitle(sub.getPaper().getTitle());
			res.setPaperSummary(sub.getPaper().getSummary());
			res.setPaperUrl(sub.getPaper().getURL());
		} else
			res.setPaperId(0);
		if (sub.getCameraPaper() != null) {
			res.setCameraId(sub.getCameraPaper().getId());
			res.setCameraTitle(sub.getCameraPaper().getTitle());
			res.setCameraSummary(sub.getCameraPaper().getSummary());
			res.setCameraUrl(sub.getCameraPaper().getURL());
		} else
			res.setCameraId(0);

		return res;
	}

	public Submission parseForm(final SubmissionForm form) {
		Submission res = new Submission();
		if (form.getId() != 0)
			res = this.findById(form.getId());
		res.setOwner(this.actorService.findById(form.getOwner()));
		res.setConference(this.conferenceService.findById(form.getConferece()));
		res.setTicker(form.getTicker());
		res.setStatus(form.getStatus());

		if (form.getCameraId() != 0)
			res.setCameraPaper(this.paperService.findById(form.getCameraId()));
		if (form.getCameraId() != 0)
			res.setPaper(this.paperService.findById(form.getPaperId()));
		return res;
	}
	public Submission initialize(final int conferenceId) {
		final Submission submission = this.create();
		submission.setOwner(this.actorService.findPrincipal());
		submission.setConference(this.conferenceService.findById(conferenceId));
		submission.setStatus(Submission.PENDING);
		submission.setTicker(this.generateTicker());
		return submission;
	}

	public String generateTicker() {
		String res;
		final Actor actor = this.actorService.findPrincipal();
		if (actor.getMiddleName() != null)
			res = actor.getName().substring(0, 1).toUpperCase() + actor.getMiddleName().substring(0, 1).toUpperCase() + actor.getSurname().substring(0, 1).toUpperCase();
		else
			res = actor.getName().substring(0, 1).toUpperCase() + "X" + actor.getSurname().substring(0, 1).toUpperCase();
		res = res + "-";
		final Random ran = new Random();
		for (int i = 0; i < 4; i++) {
			final int rand = ran.nextInt(this.dict.length());
			res = res + this.dict.charAt(rand);
		}
		return res;
	}

	public static int randInt(final int min, final int max) {
		final Random rand = new Random();

		final int randomNum = rand.nextInt((max - min) + 1) + min;

		return randomNum;
	}

	public Submission save(final Submission submission) {
		if (submission.getCameraPaper() != null)
			if (submission.getCameraPaper().getTitle() == "")
				submission.setCameraPaper(null);
		return this.submissionRepo.save(submission);
	}

	public void delete(final int id) {
		this.submissionRepo.delete(id);
	}

	public Submission findById(final int id) {
		return this.submissionRepo.findOne(id);
	}

	public List<Submission> findSubmissionByOwner(final Actor actor) {
		return this.submissionRepo.findByOwner(actor.getId());
	}

	public List<Submission> findSubmissionsByOwnerInConference(final Actor actor, final Conference conference) {
		return this.submissionRepo.findByOwnerInConference(actor.getId(), conference.getId());
	}

	public List<Submission> findByConference(final Conference conference) {
		return this.submissionRepo.findByConference(conference.getId());
	}
}
