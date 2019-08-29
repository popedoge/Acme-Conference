package services;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.SubmissionRepository;
import domain.Actor;
import domain.Conference;
import domain.Submission;
import forms.SubmissionForm;

@Service
@Transactional
public class SubmissionService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private SubmissionRepository submissionRepo;

	// Supporting services ----------------------------------------------------

	@Autowired
	private ActorService actorService;
	@Autowired
	private PaperService paperService;
	@Autowired
	private ConferenceService conferenceService;

	private String dict = "QWERTYUIOPASDFGHJKLZXCVBNM0123456789";

	public List<Submission> findAll() {
		return this.submissionRepo.findAll();
	}

	public Submission create() {
		return new Submission();
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
		} else {
			res.setPaperId(0);
		}
		if (sub.getCameraPaper() != null) {
			res.setCameraId(sub.getCameraPaper().getId());
			res.setCameraTitle(sub.getCameraPaper().getTitle());
			res.setCameraSummary(sub.getCameraPaper().getSummary());
			res.setCameraUrl(sub.getCameraPaper().getURL());
		} else {
			res.setCameraId(0);
		}

		return res;
	}

	public Submission parseForm(final SubmissionForm form) {
		Submission res = new Submission();
		if (form.getId() != 0) {
			res = this.findById(form.getId());
		}
		res.setOwner(this.actorService.findById(form.getOwner()));
		res.setConference(this.conferenceService.findById(form.getConferece()));
		res.setTicker(form.getTicker());
		res.setStatus(form.getStatus());

		if (form.getCameraId() != 0) {
			res.setCameraPaper(this.paperService.findById(form.getCameraId()));
		}
		if (form.getCameraId() != 0) {
			res.setPaper(this.paperService.findById(form.getPaperId()));
		}
		return res;
	}
	public Submission initialize(int conferenceId) {
		Submission submission = this.create();
		submission.setOwner(this.actorService.findPrincipal());
		submission.setStatus(Submission.PENDING);
		submission.setTicker(this.generateTicker());
		return submission;
	}

	public String generateTicker() {
		String res;
		Actor actor = this.actorService.findPrincipal();
		if (actor.getMiddleName() != null) {
			res = actor.getName().substring(0, 1).toUpperCase()
					+ actor.getMiddleName().substring(0, 1).toUpperCase()
					+ actor.getSurname().substring(0, 1).toUpperCase();
		} else {
			res = actor.getName().substring(0, 1).toUpperCase() + "X"
					+ actor.getSurname().substring(0, 1).toUpperCase();
		}
		res = res + "-";
		for (int i = 0; i < 4; i++) {
			Random ran = new Random();
			int rand = ran.nextInt(this.dict.length());
			res = res + String.valueOf(rand);
		}
		return res;
	}

	public static int randInt(int min, int max) {
		Random rand = new Random();

		int randomNum = rand.nextInt((max - min) + 1) + min;

		return randomNum;
	}

	public Submission save(Submission submission) {
		return this.submissionRepo.save(submission);
	}

	public void delete(int id) {
		this.submissionRepo.delete(id);
	}

	public Submission findById(int id) {
		return this.submissionRepo.findOne(id);
	}

	public List<Submission> findSubmissionByOwner(Actor actor) {
		return this.submissionRepo.findByOwner(actor.getId());
	}

	public List<Submission> findSubmissionsByOwnerInConference(Actor actor,
			Conference conference) {
		return this.submissionRepo.findByOwnerInConference(actor.getId(),
				conference.getId());
	}

	public List<Submission> findByConference(Conference conference) {
		return this.submissionRepo.findByConference(conference.getId());
	}
}
