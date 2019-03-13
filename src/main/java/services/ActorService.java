/*
 * ActorService.java
 * 
 * Copyright (C) 2019 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package services;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ActorRepository;
import security.Authority;
import security.LoginService;
import security.User;
import security.UserAccountService;
import domain.Actor;
import domain.ActorPreferences;
import forms.ActorForm;
import forms.RegisterForm;

@Service
@Transactional
public class ActorService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private ActorRepository		actorRepository;

	// Supporting services ----------------------------------------------------

	@Autowired
	private UserAccountService	userAccountService;
	@Autowired
	private MemberService		memberService;
	@Autowired
	private AdminService		adminService;
	@Autowired
	private LoginService		loginService;


	// Constructors -----------------------------------------------------------

	public ActorService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Actor findById(final int id) {
		return this.actorRepository.findOne(id);
	}

	public ActorForm formatForm(final Actor actor) {
		final ActorForm res = new ActorForm();
		res.setFirstName(actor.getName());
		res.setLastName(actor.getSurname());
		res.setEmail(actor.getEmail());
		res.setAddress(actor.getAddress());
		res.setPhoto(actor.getPhoto());
		res.setPhoneNumber(actor.getPhoneNumber());
		res.setId(actor.getId());
		res.setUsername(actor.getUser().getUsername());
		return res;
	}

	public Actor parseForm(final ActorForm form) {
		final Actor res;
		res = this.findById(form.getId());
		res.setAddress(form.getAddress());
		res.setEmail(form.getEmail());
		res.setPhoneNumber(form.getPhoneNumber());
		res.setPhoto(form.getPhoto());
		res.setName(form.getFirstName());
		res.setSurname(form.getLastName());
		res.getUser().setUsername(form.getUsername());
		return res;
	}

	public ActorForm formatForm(final Actor actor, final ActorPreferences preferences) {
		final ActorForm res = new ActorForm();
		if (preferences.getDisplayAddress())
			res.setAddress(actor.getAddress());
		if (preferences.getDisplayEmail())
			res.setEmail(actor.getEmail());
		if (preferences.getDisplayPhoneNumber())
			res.setPhoneNumber(actor.getPhoneNumber());
		if (preferences.getDisplayRealName()) {
			res.setFirstName(actor.getName());
			res.setLastName(actor.getSurname());
		}
		res.setUsername(actor.getSurname());
		res.setPhoto(actor.getPhoto());
		return res;
	}

	public Actor register(final RegisterForm form) {
		Actor res = null;
		switch (form.getRole()) {
		case "MEMBER":
			res = this.memberService.create();
		case "ADMIN":

		}
		if (res != null) {
			res.setAddress(form.getForm().getAddress());
			res.setEmail(form.getForm().getEmail());
			res.setPhoneNumber(form.getForm().getPhoneNumber());
			if (form.getForm().getPhoto() != "")
				res.setPhoto(form.getForm().getPhoto());
			res.getUser().setUsername(form.getForm().getUsername());
			res.setName(form.getForm().getFirstName());
			res.setSurname(form.getForm().getLastName());

			final Md5PasswordEncoder encoder = new Md5PasswordEncoder();
			res.getUser().setPassword(encoder.encodePassword(form.getPassword(), null));
		}
		final Actor saved = this.save(res);

		return saved;
	}

	public Collection<Actor> findAll() {
		Collection<Actor> result;

		result = this.actorRepository.findAll();
		Assert.notNull(result);

		return result;
	}

	public Actor findOne(final int actorId) {
		Assert.isTrue(actorId != 0);

		Actor result;

		result = this.actorRepository.findOne(actorId);
		Assert.notNull(result);

		return result;
	}

	public Actor save(final Actor actor) {
		Assert.notNull(actor);

		Actor result;

		result = this.actorRepository.save(actor);

		return result;
	}

	public void delete(final Actor actor) {
		Assert.notNull(actor);
		Assert.isTrue(actor.getId() != 0);
		Assert.isTrue(this.actorRepository.exists(actor.getId()));

		this.actorRepository.delete(actor);
	}

	// Other business methods -------------------------------------------------

	public User findUserAccount(final Actor actor) {
		Assert.notNull(actor);

		User result;

		result = this.userAccountService.findByActor(actor);

		return result;
	}

	public Actor initialize(final Actor actor, final String authority) {
		actor.setPhoto("https://www.qualiscare.com/wp-content/uploads/2017/08/default-user-300x300.png");
		actor.setUser(this.userAccountService.createUser(authority));

		return actor;
	}

	public void assertPrincipalAuthority(final String auth) {
		Assert.isTrue(this.getPrincipalAuthority().contains(auth), "The user logged does not have authority to do this action.");

	}

	public Actor findPrincipal() {
		return this.actorRepository.findByUser(LoginService.getPrincipal().getId());
	}

	public Collection<String> getPrincipalAuthority() {
		final Collection<Authority> auth = this.findPrincipal().getUser().getAuthorities();
		final Collection<String> res = new HashSet<>();
		for (final Authority a : auth)
			res.add(a.getAuthority());
		return res;
	}

	public Collection<String> getAuthority(final Actor actor) {
		final Collection<Authority> auth = actor.getUser().getAuthorities();
		final Collection<String> res = new HashSet<>();
		for (final Authority a : auth)
			res.add(a.getAuthority());
		return res;
	}

	public Actor findByUsername(final String username) {
		Actor res = null;
		try {
			final User user = this.userAccountService.findByUsername(username);
			res = this.findByUser(user);
		} catch (final Exception e) {

		}
		return res;
	}

	public Actor findByUser(final User user) {
		return this.actorRepository.findByUser(user.getId());
	}

}
