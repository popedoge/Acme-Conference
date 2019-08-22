package services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.ReviewerRepository;
import security.User;
import security.UserAccountService;
import domain.ActorPreferences;
import domain.Reviewer;
import forms.RegisterForm;

@Service
@Transactional
public class ReviewerService {

	@Autowired
	private ReviewerRepository ReviewerRepo;
	@Autowired
	private ActorService actorService;
	@Autowired
	private UserAccountService userService;
	@Autowired
	private ActorPreferencesService preferenceService;
	@Autowired
	private SiteConfigurationService siteConfigService;

	public Reviewer findById(final int id) {
		return this.ReviewerRepo.findOne(id);
	}

	public List<Reviewer> findAll() {
		return this.ReviewerRepo.findAll();
	}

	public Reviewer create(User user) {
		Reviewer reviewer = new Reviewer();
		reviewer.setPhoto("https://www.qualiscare.com/wp-content/uploads/2017/08/default-user-300x300.png");
		reviewer.setUser(user);
		return reviewer;
	}

	public Reviewer register(final RegisterForm form) {

		// create user
		User user = this.userService.createUser(form.getForm().getRole());
		user.setUsername(form.getForm().getUsername());
		final Md5PasswordEncoder encoder = new Md5PasswordEncoder();
		user.setPassword(encoder.encodePassword(form.getPassword(), null));
		Reviewer res = this.create(user);
		// actor
		res.setAddress(form.getForm().getAddress());
		res.setEmail(form.getForm().getEmail());
		res.setPhoneNumber(form.getForm().getPhoneNumber());
		if (form.getForm().getPhoto() != "")
			res.setPhoto(form.getForm().getPhoto());
		res.setName(form.getForm().getFirstName());
		res.setSurname(form.getForm().getLastName());
		res.setExpertise(form.getForm().getExpertise());
		final Reviewer saved = this.save(res);
		// preferences
		ActorPreferences preferences = this.preferenceService.create(saved);
		this.preferenceService.save(preferences);

		return saved;
	}

	public Reviewer setReviewerity(final Reviewer Reviewer) {
		Reviewer.setUser(this.userService.addAuthority(Reviewer.getUser(),
				"Reviewer"));
		return Reviewer;
	}

	public Reviewer findPrincipal() {
		this.actorService.assertPrincipalAuthority("Reviewer");
		return (Reviewer) this.actorService.findPrincipal();
	}

	public Reviewer save(final Reviewer Reviewer) {
		// if it's saved for the first time (created), assign a proper make
		// given his name

		if (Reviewer.getPhoneNumber().matches("^\\d{4,}$")) {
			String phonenum = "+"
					+ String.valueOf(this.siteConfigService.find()
							.getCountryCode()) + " "
					+ Reviewer.getPhoneNumber();
			Reviewer.setPhoneNumber(phonenum);
		}
		final Reviewer res = this.ReviewerRepo.save(Reviewer);
		return res;
	}

}
