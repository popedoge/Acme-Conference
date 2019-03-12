
package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.MemberRepository;
import security.UserAccountService;
import domain.Member;

@Service
@Transactional
public class MemberService {

	@Autowired
	private MemberRepository	memberRepo;
	@Autowired
	private ActorService		actorService;
	@Autowired
	private UserAccountService	userService;


	public Member findById(final int id) {
		return this.memberRepo.findOne(id);
	}

	public Member create() {
		Member member = new Member();
		member = (Member) this.actorService.initialize(member, "MEMBER");
		return member;
	}

	public Member setAuthority(final Member member) {
		member.setUser(this.userService.addAuthority(member.getUser(), "MEMBER"));
		return member;
	}

	public Member findPrincipal() {
		this.actorService.assertPrincipalAuthority("MEMBER");
		return (Member) this.actorService.findPrincipal();
	}

	public Member save(final Member member) {
		// if it's saved for the first time (created), assign a proper make given his name
		final Member res = this.memberRepo.save(member);
		return res;
	}

}
