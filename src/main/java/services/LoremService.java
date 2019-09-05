
package services;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.Lorem;
import repositories.LoremRepository;

@Service
@Transactional
public class LoremService {

	@Autowired
	private LoremRepository		loremRepo;
	@Autowired
	private ConferenceService	referenceService;

	private final String		dict			= "QWERTYUIOPASDFGHJKLZXCVBNM0123456789";
	private final Integer		tickerLength	= 6;


	public Lorem create() {
		final Lorem res = new Lorem();
		res.setLocked(false);
		return res;
	}

	public Lorem lock(final int id) {
		final Lorem lorem = this.findById(id);
		lorem.setLocked(true);
		return this.save(lorem);
	}

	public Lorem save(final Lorem lorem) {
		return this.loremRepo.save(lorem);
	}

	public Lorem findById(final int id) {
		return this.loremRepo.findOne(id);
	}

	public List<Lorem> findAll() {
		return this.loremRepo.findAll();
	}

	public List<Lorem> findByReference(final int id) {
		return this.loremRepo.findByReference(id);
	}

	public List<Lorem> findFinalByReference(final int id) {
		return this.loremRepo.findFinalByReference(id);
	}

	public List<Lorem> findFinal() {
		return this.loremRepo.findFinal();
	}

	public void delete(final int id) {
		this.loremRepo.delete(id);
	}

	public void deleteByReference(final int id) {
		this.loremRepo.delete(this.findByReference(id));
	}

	//TODO:generate ticker to match pattern
	public String generateTicker() {
		String res = "";
		//random character generation
		final Random ran = new Random();
		for (int i = 0; i < this.tickerLength; i++) {
			final int rand = ran.nextInt(this.dict.length());
			res = res + this.dict.charAt(rand);
		}
		return res;
	}
}
