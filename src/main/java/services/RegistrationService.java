package services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.RegistrationRepository;
import domain.Registration;

@Service
@Transactional
public class RegistrationService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private RegistrationRepository registrationRepo;

	// Supporting services ----------------------------------------------------

	@Autowired
	private ActorService actorService;

	public Registration create() {
		return new Registration();
	}

	public Registration save(Registration registration) {
		return this.registrationRepo.save(registration);
	}

	public void delete(int id) {
		this.registrationRepo.delete(id);
	}

	public Registration findById(int id) {
		return this.registrationRepo.findOne(id);
	}

	public List<Registration> findByOwner(int id) {
		return this.registrationRepo.findByOwner(id);
	}

	public List<Registration> findByConference(int id) {
		return this.registrationRepo.findByConference(id);
	}
}
