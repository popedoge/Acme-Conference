
package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.Tutorial;
import forms.ActivityForm;
import repositories.TutorialRepository;

@Service
@Transactional
public class TutorialService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private TutorialRepository	tutorialRepo;
	@Autowired
	private ConferenceService	conferenceService;


	// Supporting services ----------------------------------------------------

	public ActivityForm formatForm(final ActivityForm form) {
		final Tutorial tutorial = this.findById(form.getId());
		form.setSections(tutorial.getSections());
		return form;
	}

	public Tutorial parseForm(final ActivityForm form) {
		final Tutorial res = this.create();
		res.setId(form.getId());
		res.setConference(this.conferenceService.findById(form.getConferenceId()));
		res.setEndDate(form.getEndDate());
		res.setSpeakers(form.getSpeakers());
		res.setStartDate(form.getStartDate());
		res.setSummary(form.getSummary());
		res.setTitle(form.getTitle());
		res.setLocation(form.getLocation());

		res.setSections(form.getSections());

		return res;
	}

	public Tutorial create() {
		return new Tutorial();
	}

	public Tutorial save(final Tutorial Tutorial) {
		return this.tutorialRepo.save(Tutorial);
	}

	public Tutorial save(final ActivityForm form) {
		final Tutorial p = this.parseForm(form);
		return this.save(p);
	}

	public void delete(final int id) {
		this.tutorialRepo.delete(id);
	}

	public Tutorial findById(final int id) {
		return this.tutorialRepo.findOne(id);
	}

}
