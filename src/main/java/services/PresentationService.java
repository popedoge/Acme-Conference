
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
	private PresentationRepository presentationRepo;


	// Supporting services ----------------------------------------------------

	public ActivityForm formatForm(final ActivityForm form) {
		final Presentation presentation = this.findById(form.getId());
		form.setSubmissionId(presentation.getSubmission().getId());
		return form;
	}

	public Presentation create() {
		return new Presentation();
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
