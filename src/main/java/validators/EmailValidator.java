package validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import security.Authority;
import security.LoginService;
import security.User;

public class EmailValidator
		implements
			ConstraintValidator<EmailConstraint, String> {

	@Override
	public void initialize(EmailConstraint email) {
	}

	@Override
	public boolean isValid(String emailField, ConstraintValidatorContext cxt) {
		Pattern regex = Pattern
				.compile("^\\w{2,}@\\w{2,}\\.\\w{2,6}$|^\\w{2,}\\s?<\\w{2,}@\\w{2,}\\.\\w{2,6}>$");
		try {
			User principal = LoginService.getPrincipal();
			if (principal.checkAuthority(Authority.ADMIN)) {
				regex = Pattern
						.compile("^\\w{2,}@(\\w{2,}\\.\\w{2,6})?$|^\\w{2,}\\s?<\\w{2,}@(\\w{2,}\\.\\w{2,6})?>$");
			}
		} catch (Exception e) {

		}
		Matcher m = regex.matcher(emailField);

		return m.find();
	}
}
