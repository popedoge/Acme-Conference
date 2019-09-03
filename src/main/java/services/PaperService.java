
package services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.Conference;
import domain.Paper;
import repositories.PaperRepository;

@Service
@Transactional
public class PaperService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private PaperRepository	paperRepo;

	// Supporting services ----------------------------------------------------

	@Autowired
	private ActorService	actorService;


	public Paper create() {
		return new Paper();
	}

	public Paper save(final Paper paper) {
		//refuse save on camera ready when after DL
		return this.paperRepo.save(paper);
	}

	public void delete(final int id) {
		this.paperRepo.delete(id);
	}

	public Paper findById(final int id) {
		return this.paperRepo.findOne(id);
	}

	public List<Paper> findUnusedByUser(final Conference conference) {
		final List<Paper> res = new ArrayList<>();
		return res;
	}
}
