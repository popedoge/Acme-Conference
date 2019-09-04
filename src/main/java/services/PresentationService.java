
package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.Presentation;
import forms.ActivityForm;
import repositories.PresentationRepository;

@Service
@Transactional
public class PresentationService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private PresentationRepository	presentationRepo;
	@Autowired
	private ConferenceService		conferenceService;


	// Supporting services ----------------------------------------------------

	public ActivityForm formatForm(final ActivityForm form) {
		final Presentation presentation = this.findById(form.getId());
		if (presentation != null)
			form.setSubmission(presentation.getSubmission());
		return form;
	}

	public Presentation parseForm(final ActivityForm form) {
		final Presentation res = this.create();
		res.setId(form.getId());
		res.setEndDate(form.getEndDate());
		res.setConference(this.conferenceService.findById(form.getConferenceId()));
		res.setSpeakers(form.getSpeakers());
		res.setStartDate(form.getStartDate());
		res.setSummary(form.getSummary());
		res.setTitle(form.getTitle());
		res.setLocation(form.getLocation());

		res.setSubmission(form.getSubmission());
		return res;
	}

	public Presentation create() {
		return new Presentation();
	}

	public Presentation save(final ActivityForm form) {
		final Presentation p = this.parseForm(form);
		return this.save(p);
	}

	public Presentation save(final Presentation Presentation) {
		return this.presentationRepo.save(Presentation);
	}

	public void delete(final int id) {
		this.presentationRepo.delete(id);
	}

	public Presentation findById(final int id) {
		return this.presentationRepo.findOne(id);
	}

}
