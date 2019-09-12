
package services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.ActorPreferences;
import domain.Member;
import forms.RegisterForm;
import repositories.MemberRepository;
import security.Authority;
import security.User;
import security.UserAccountService;

@Service
@Transactional
public class MemberService {

	@Autowired
	private ActorService			actorService;
	@Autowired
	private UserAccountService		userService;
	@Autowired
	private ActorPreferencesService	preferenceService;
	@Autowired
	private MemberRepository		memberRepo;


	public Member findById(final int id) {
		return this.memberRepo.findOne(id);
	}

	public List<Member> findAll() {
		return this.memberRepo.findAll();
	}

	public Member create(final User user) {
		final Member member = new Member();
		member.setAvatar("https://theresolutioncentre.co.uk/wp-content/uploads/2018/06/profile.png");
		member.setUser(user);
		return member;
	}

	public Member register(final RegisterForm form) {

		// create user
		final User user = this.userService.createUser(form.getForm().getRole());
		user.setUsername(form.getForm().getUsername());
		final Md5PasswordEncoder encoder = new Md5PasswordEncoder();
		user.setPassword(encoder.encodePassword(form.getPassword(), null));
		final Member res = this.create(user);
		// actor
		res.setAvatar(form.getForm().getAvatar());
		res.setEmail(form.getForm().getEmail());
		res.setBio(form.getForm().getBio());

		final Member saved = this.save(res);
		// preferences
		final ActorPreferences preferences = this.preferenceService.create(saved);
		this.preferenceService.save(preferences);

		return saved;
	}

	public Member findPrincipal() {
		this.actorService.assertPrincipalAuthority(Authority.MEMBER);
		return (Member) this.actorService.findPrincipal();
	}

	public Member save(final Member member) {
		return this.memberRepo.saveAndFlush(member);
	}

}
