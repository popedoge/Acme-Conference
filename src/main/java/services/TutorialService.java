
package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.Tutorial;
import repositories.TutorialRepository;

@Service
@Transactional
public class TutorialService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private TutorialRepository TutorialRepo;


	// Supporting services ----------------------------------------------------

	public Tutorial create() {
		return new Tutorial();
	}

	public Tutorial save(final Tutorial Tutorial) {
		return this.TutorialRepo.save(Tutorial);
	}

	public void delete(final int id) {
		this.TutorialRepo.delete(id);
	}

	public Tutorial findById(final int id) {
		return this.TutorialRepo.findOne(id);
	}

}
