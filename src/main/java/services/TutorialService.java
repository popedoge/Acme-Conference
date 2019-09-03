
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
	private TutorialRepository tutorialRepo;


	// Supporting services ----------------------------------------------------

	public ActivityForm formatForm(final ActivityForm form) {
		final Tutorial tutorial = this.findById(form.getId());
		form.setSections(tutorial.getSections());
		return form;
	}

	public Tutorial create() {
		return new Tutorial();
	}

	public Tutorial save(final Tutorial Tutorial) {
		return this.tutorialRepo.save(Tutorial);
	}

	public void delete(final int id) {
		this.tutorialRepo.delete(id);
	}

	public Tutorial findById(final int id) {
		return this.tutorialRepo.findOne(id);
	}

}
