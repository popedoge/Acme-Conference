
package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.Panel;
import repositories.PanelRepository;

@Service
@Transactional
public class PanelService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private PanelRepository PanelRepo;


	// Supporting services ----------------------------------------------------

	public Panel create() {
		return new Panel();
	}

	public Panel save(final Panel Panel) {
		return this.PanelRepo.save(Panel);
	}

	public void delete(final int id) {
		this.PanelRepo.delete(id);
	}

	public Panel findById(final int id) {
		return this.PanelRepo.findOne(id);
	}

}
