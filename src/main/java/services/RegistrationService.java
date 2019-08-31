
package services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.RegistrationRepository;
import domain.Conference;
import domain.CreditCard;
import domain.Registration;

@Service
@Transactional
public class RegistrationService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private RegistrationRepository	registrationRepo;

	// Supporting services ----------------------------------------------------

	@Autowired
	private ActorService			actorService;
	@Autowired
	private AuthorService			authorService;


	public Registration create(final Conference conference) {
		this.authorService.findPrincipal();
		final Registration registration = new Registration();
		final CreditCard card = new CreditCard();
		registration.setConference(conference);
		registration.setOwner(this.actorService.findPrincipal());
		registration.setCreditCard(card);
		return registration;
	}

	public Registration save(final Registration registration) {
		this.authorService.findPrincipal();
		return this.registrationRepo.save(registration);
	}

	public void delete(final int id) {
		this.registrationRepo.delete(id);
	}

	public Registration findById(final int id) {
		return this.registrationRepo.findOne(id);
	}

	public List<Registration> findByOwner(final int id) {
		return this.registrationRepo.findByOwner(id);
	}

	public List<Registration> findByConference(final int id) {
		return this.registrationRepo.findByConference(id);
	}
}
