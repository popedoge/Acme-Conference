
package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.Presentation;
import repositories.PresentationRepository;

@Service
@Transactional
public class PresentationService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private PresentationRepository PresentationRepo;


	// Supporting services ----------------------------------------------------

	public Presentation create() {
		return new Presentation();
	}

	public Presentation save(final Presentation Presentation) {
		return this.PresentationRepo.save(Presentation);
	}

	public void delete(final int id) {
		this.PresentationRepo.delete(id);
	}

	public Presentation findById(final int id) {
		return this.PresentationRepo.findOne(id);
	}

}
