
package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.AdministratorRepository;
import security.UserAccountService;
import domain.Administrator;

@Service
@Transactional
public class AdminService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private AdministratorRepository	adminRepository;

	// Supporting services ----------------------------------------------------

	@Autowired
	private UserAccountService		userAccountService;
	@Autowired
	private ActorService			actorService;


	public Administrator findPrincipal() {
		this.actorService.assertPrincipalAuthority("ADMIN");
		return (Administrator) this.actorService.findPrincipal();
	}

	public Administrator save(final Administrator admin) {
		// if it's saved for the first time (created), assign a proper make given his name
		final Administrator res = this.adminRepository.save(admin);
		return res;

	}

}
