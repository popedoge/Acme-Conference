package controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import security.Authority;
import security.UserAccountService;
import services.ActorService;
import services.AuthorService;
import services.ReviewerService;
import domain.Author;
import domain.Reviewer;
import forms.RegisterForm;

@Controller
@RequestMapping("/register")
public class RegisterController {

	@Autowired
	private AuthorService authorService;
	@Autowired
	private ActorService actorService;
	@Autowired
	private ReviewerService reviewerService;
	@Autowired
	private UserAccountService userAccountService;

	@RequestMapping(value = "/init", method = RequestMethod.GET)
	public ModelAndView createUser() {
		final Author author = this.authorService.create(this.userAccountService
				.createUser(Authority.AUTHOR));
		final RegisterForm regForm = new RegisterForm();
		regForm.setForm(this.actorService.formatForm(author));
		regForm.getForm().setRole(Authority.AUTHOR);
		regForm.setIsReviewer(false);
		return this.createActorEditModelAndView(regForm);
	}

	@RequestMapping(value = "/init-reviewer", method = RequestMethod.GET)
	public ModelAndView createReviewer() {
		final Reviewer reviewer = this.reviewerService
				.create(this.userAccountService.createUser(Authority.REVIEWER));
		final RegisterForm regForm = new RegisterForm();
		regForm.setForm(this.actorService.formatForm(reviewer));
		regForm.getForm().setRole(Authority.REVIEWER);
		regForm.setIsReviewer(true);
		return this.createActorEditModelAndView(regForm);
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ModelAndView saveUser(
			@ModelAttribute("regForm") @Valid final RegisterForm form,
			final BindingResult binding) {
		ModelAndView res;
		if (binding.hasErrors())
			return this.createActorEditModelAndView(form);
		else
			try {
				Assert.isTrue(form.getForm().getRole() != null
						&& form.getForm().getRole() != "",
						"Error registering: no role specified");
				switch (form.getForm().getRole()) {
					case Authority.AUTHOR :
						this.authorService.register(form);
					case Authority.REVIEWER :
						this.reviewerService.register(form);
				}
				res = new ModelAndView("redirect:../security/login.do");
			} catch (final Exception e) {
				res = this.createActorEditModelAndView(form, "register.error");
			}
		return res;
	}
	// AUX // ============================================ //
	protected ModelAndView createActorEditModelAndView(
			final RegisterForm regForm) {
		return this.createActorEditModelAndView(regForm, null);
	}

	protected ModelAndView createActorEditModelAndView(
			final RegisterForm regForm, final String messageCode) {
		ModelAndView result;

		result = new ModelAndView("security/register");
		result.addObject("regForm", regForm);
		result.addObject("message", messageCode);
		return result;
	}

}
