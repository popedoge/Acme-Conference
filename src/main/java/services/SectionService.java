
package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.Section;
import repositories.SectionRepository;

@Service
@Transactional
public class SectionService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private SectionRepository sectionRepo;


	// Supporting services ----------------------------------------------------

	public Section create() {
		return new Section();
	}

	public Section save(final Section section) {
		//refuse save on camera ready when after DL
		return this.sectionRepo.save(section);
	}

	public void delete(final int id) {
		this.sectionRepo.delete(id);
	}

	public Section findById(final int id) {
		return this.sectionRepo.findOne(id);
	}

}
