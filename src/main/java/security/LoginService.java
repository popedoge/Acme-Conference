/*
 * LoginService.java
 * 
 * Copyright (C) 2019 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service
@Transactional
public class LoginService implements UserDetailsService {

	// Managed repository -----------------------------------------------------

	@Autowired
	UserAccountRepository	userRepository;


	// Business methods -------------------------------------------------------

	@Override
	public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
		Assert.notNull(username);

		UserDetails result;

		result = this.userRepository.findByUsername(username);
		Assert.notNull(result);
		// WARNING: The following sentences prevent lazy initialisation problems!
		Assert.notNull(result.getAuthorities());
		result.getAuthorities().size();

		return result;
	}

	//	@Override
	//	public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
	//
	//		final User user = this.userRepository.findByUsername(username);
	//		if (user == null)
	//			return new org.springframework.security.core.userdetails.User(" ", " ", true, true, true, true, this.getAuthorities(Arrays.asList(this.roleRepository.findByName("ROLE_USER"))));
	//
	//		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), user.isEnabled(), true, true, true, this.getAuthorities(user.getRoles()));
	//	}
	//
	//	private Collection<? extends GrantedAuthority> getAuthorities(final Collection<Role> roles) {
	//
	//		return this.getGrantedAuthorities(this.getPrivileges(roles));
	//	}
	//
	//	private List<String> getPrivileges(final Collection<Role> roles) {
	//
	//		final List<String> privileges = new ArrayList<>();
	//		final List<Privilege> collection = new ArrayList<>();
	//		for (final Role role : roles)
	//			collection.addAll(role.getPrivileges());
	//		for (final Privilege item : collection)
	//			privileges.add(item.getName());
	//		return privileges;
	//	}
	//
	//	private List<GrantedAuthority> getGrantedAuthorities(final List<String> privileges) {
	//		final List<GrantedAuthority> authorities = new ArrayList<>();
	//		for (final String privilege : privileges)
	//			authorities.add(new SimpleGrantedAuthority(privilege));
	//		return authorities;
	//	}

	public static User getPrincipal() {
		User result;
		SecurityContext context;
		Authentication authentication;
		Object principal;

		// If the asserts in this method fail, then you're
		// likely to have your Tomcat's working directory
		// corrupt. Please, clear your browser's cache, stop
		// Tomcat, update your Maven's project configuration,
		// clean your project, clean Tomcat's working directory,
		// republish your project, and start it over.

		context = SecurityContextHolder.getContext();
		Assert.notNull(context);
		authentication = context.getAuthentication();
		Assert.notNull(authentication);
		principal = authentication.getPrincipal();
		Assert.isTrue(principal instanceof User);
		result = (User) principal;
		Assert.notNull(result);
		Assert.isTrue(result.getId() != 0);

		return result;
	}

	//check password
	public boolean checkPassword(final String pass) {
		boolean res = false;
		final Md5PasswordEncoder encoder = new Md5PasswordEncoder();
		final String hash = encoder.encodePassword(pass, null);
		final User user = LoginService.getPrincipal();
		if (user.getPassword().contains(hash))
			res = true;
		return res;
	}

}
