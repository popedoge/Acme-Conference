
package services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.AuthorRepository;
import security.User;
import security.UserAccountService;
import domain.ActorPreferences;
import domain.Author;
import forms.RegisterForm;

@Service
@Transactional
public class AuthorService {

	@Autowired
	private AuthorRepository			authorRepo;
	@Autowired
	private ActorService				actorService;
	@Autowired
	private UserAccountService			userService;
	@Autowired
	private ActorPreferencesService		preferenceService;
	@Autowired
	private SiteConfigurationService	siteConfigService;
	@Autowired
	private UserAccountService			userAccountService;


	public Author findById(final int id) {
		return this.authorRepo.findOne(id);
	}

	public List<Author> findAll() {
		return this.authorRepo.findAll();
	}

	public Author create(final User user) {
		final Author author = new Author();
		author.setPhoto("https://www.qualiscare.com/wp-content/uploads/2017/08/default-user-300x300.png");
		author.setUser(user);
		return author;
	}

	public Author register(final RegisterForm form) {

		// create user
		final User user = this.userService.createUser(form.getForm().getRole());
		user.setUsername(form.getForm().getUsername());
		final Md5PasswordEncoder encoder = new Md5PasswordEncoder();
		user.setPassword(encoder.encodePassword(form.getPassword(), null));
		final Author res = this.create(user);
		// actor
		res.setAddress(form.getForm().getAddress());
		res.setEmail(form.getForm().getEmail());
		res.setMiddleName(form.getForm().getMiddleName());
		res.setPhoneNumber(form.getForm().getPhoneNumber());
		if (form.getForm().getPhoto() != "")
			res.setPhoto(form.getForm().getPhoto());
		res.setName(form.getForm().getFirstName());
		res.setSurname(form.getForm().getLastName());
		final Author saved = this.save(res);
		// preferences
		final ActorPreferences preferences = this.preferenceService.create(saved);
		this.preferenceService.save(preferences);

		return saved;
	}

	public Author findPrincipal() {
		this.actorService.assertPrincipalAuthority("AUTHOR");
		return (Author) this.actorService.findPrincipal();
	}

	public Author save(final Author author) {
		// if it's saved for the first time (created), assign a proper make
		// given his name
		if (author.getPhoneNumber().matches("^\\d{4,}$")) {
			final String phonenum = "+" + String.valueOf(this.siteConfigService.find().getCountryCode()) + " " + author.getPhoneNumber();
			author.setPhoneNumber(phonenum);
		}
		return this.authorRepo.save(author);
	}

}
