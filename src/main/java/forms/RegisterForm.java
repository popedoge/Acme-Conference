
package forms;

import javax.validation.Valid;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class RegisterForm {

	private ActorForm	form;
	private String		password;
	private Boolean		acceptTerms;


	@AssertTrue(message = "terms.error")
	public Boolean getAcceptTerms() {
		return this.acceptTerms;
	}

	public void setAcceptTerms(final Boolean acceptTerms) {
		this.acceptTerms = acceptTerms;
	}

	@Valid
	@NotNull
	public ActorForm getForm() {
		return this.form;
	}

	public void setForm(final ActorForm form) {
		this.form = form;
	}

	@Size(min = 4, max = 32, message = "Password must have 4 to 32 characters")
	@Pattern(regexp = "^[a-zA-Z0-9_]*$", message = "Characters must be alphanumeric")
	public String getPassword() {
		return this.password;
	}

	public void setPassword(final String password) {
		this.password = password;
	}

}
