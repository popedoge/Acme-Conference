package services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.PaperRepository;
import domain.Conference;
import domain.Paper;

@Service
@Transactional
public class PaperService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private PaperRepository paperRepo;

	// Supporting services ----------------------------------------------------

	@Autowired
	private ActorService actorService;

	public Paper create() {
		return new Paper();
	}

	public Paper save(Paper paper) {
		return this.paperRepo.save(paper);
	}

	public void delete(int id) {
		this.paperRepo.delete(id);
	}

	public Paper findById(int id) {
		return this.paperRepo.findOne(id);
	}

	public List<Paper> findUnusedByUser(Conference conference) {
		List<Paper> res = new ArrayList<>();
		return res;
	}
}
