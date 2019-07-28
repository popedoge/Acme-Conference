package services;

import java.util.List;

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

	public List<Author> findAll() {
		return this.authorRepo.findAll();
	}

	public Author create() {
		Author author = new Author();
		author = (Author) this.actorService.initialize(author, "AUTHOR");
		return author;
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

	public Author setAuthority(final Author author) {
		author.setUser(this.userService.addAuthority(author.getUser(), "AUTHOR"));
		return author;
	}

	public Author findPrincipal() {
		this.actorService.assertPrincipalAuthority("AUTHOR");
		return (Author) this.actorService.findPrincipal();
	}

	public Author save(final Author author) {
		// if it's saved for the first time (created), assign a proper make
		// given his name
		final Author res = this.authorRepo.save(author);
		return res;
	}

}
