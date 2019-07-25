package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.MemberRepository;
import security.UserAccountService;
import domain.ActorPreferences;
import domain.Member;
import forms.RegisterForm;

@Service
@Transactional
public class MemberService {

	@Autowired
	private MemberRepository memberRepo;
	@Autowired
	private ActorService actorService;
	@Autowired
	private UserAccountService userService;
	@Autowired
	private ActorPreferencesService preferenceService;

	public Member findById(final int id) {
		return this.memberRepo.findOne(id);
	}

	public Member create() {
		Member member = new Member();
		member = (Member) this.actorService.initialize(member, "MEMBER");
		return member;
	}

	public Member register(final RegisterForm form) {
		final Member res = this.create();

		if (res != null) {
			// actor
			res.setAddress(form.getForm().getAddress());
			res.setEmail(form.getForm().getEmail());
			res.setPhoneNumber(form.getForm().getPhoneNumber());
			if (form.getForm().getPhoto() != "")
				res.setPhoto(form.getForm().getPhoto());
			res.setName(form.getForm().getFirstName());
			res.setSurname(form.getForm().getLastName());
			// user
			res.getUser().setUsername(form.getForm().getUsername());
			final Md5PasswordEncoder encoder = new Md5PasswordEncoder();
			res.getUser().setPassword(
					encoder.encodePassword(form.getPassword(), null));
		}
		final Member saved = this.save(res);
		// TODO: FIX THIS
		ActorPreferences preferences = this.preferenceService.create(saved);
		this.preferenceService.save(preferences);
		return saved;
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
		// if it's saved for the first time (created), assign a proper make
		// given his name
		final Member res = this.memberRepo.save(member);
		return res;
	}

}
