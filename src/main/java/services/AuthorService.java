package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.AuthorRepository;
import security.UserAccountService;
import domain.ActorPreferences;
import domain.Author;
import forms.RegisterForm;

@Service
@Transactional
public class AuthorService {

	@Autowired
	private AuthorRepository authorRepo;
	@Autowired
	private ActorService actorService;
	@Autowired
	private UserAccountService userService;
	@Autowired
	private ActorPreferencesService preferenceService;

	public Author findById(final int id) {
		return this.authorRepo.findOne(id);
	}

	public Author create() {
		Author member = new Author();
		member = (Author) this.actorService.initialize(member, "MEMBER");
		return member;
	}

	public Author register(final RegisterForm form) {
		final Author res = this.create();

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
		final Author saved = this.save(res);

		ActorPreferences preferences = this.preferenceService.create(saved);
		this.preferenceService.save(preferences);
		return saved;
	}

	public Author setAuthority(final Author member) {
		member.setUser(this.userService.addAuthority(member.getUser(), "MEMBER"));
		return member;
	}

	public Author findPrincipal() {
		this.actorService.assertPrincipalAuthority("MEMBER");
		return (Author) this.actorService.findPrincipal();
	}

	public Author save(final Author member) {
		// if it's saved for the first time (created), assign a proper make
		// given his name
		final Author res = this.authorRepo.save(member);
		return res;
	}

}
