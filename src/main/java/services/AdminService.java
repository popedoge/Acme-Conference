
package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.Administrator;
import repositories.AdministratorRepository;

@Service
@Transactional
public class AdminService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private AdministratorRepository	adminRepository;

	// Supporting services ----------------------------------------------------

	@Autowired
	private ActorService			actorService;
	@Autowired
	private MemberService			memberService;


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
