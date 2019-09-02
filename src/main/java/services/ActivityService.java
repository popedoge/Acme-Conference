
package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.Activity;
import repositories.ActivityRepository;

@Service
@Transactional
public class ActivityService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private ActivityRepository ActivityRepo;


	// Supporting services ----------------------------------------------------

	public Activity create() {
		return new Activity();
	}

	public Activity save(final Activity Activity) {
		return this.ActivityRepo.save(Activity);
	}

	public void delete(final int id) {
		this.ActivityRepo.delete(id);
	}

	public Activity findById(final int id) {
		return this.ActivityRepo.findOne(id);
	}

}
