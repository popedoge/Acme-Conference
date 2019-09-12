
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

import domain.Member;
import forms.RegisterForm;
import security.Authority;
import security.UserAccountService;
import services.ActorService;
import services.MemberService;

@Controller
@RequestMapping("/register")
public class RegisterController {

	@Autowired
	private ActorService		actorService;
	@Autowired
	private MemberService		memberService;
	@Autowired
	private UserAccountService	userAccountService;


	@RequestMapping(value = "/init", method = RequestMethod.GET)
	public ModelAndView createUser() {
		final Member member = this.memberService.create(this.userAccountService.createUser(Authority.MEMBER));
		final RegisterForm regForm = new RegisterForm();
		regForm.setForm(this.actorService.formatForm(member));
		regForm.getForm().setRole(Authority.MEMBER);
		return this.createActorEditModelAndView(regForm);
	}

	//	@RequestMapping(value = "/init-reviewer", method = RequestMethod.GET)
	//	public ModelAndView createReviewer() {
	//		final Customer customer = this.memberService.create(this.userAccountService.createUser(Authority.REVIEWER));
	//		final RegisterForm regForm = new RegisterForm();
	//		regForm.setForm(this.actorService.formatForm(customer));
	//		regForm.getForm().setRole(Authority.REVIEWER);
	//		return this.createActorEditModelAndView(regForm);
	//	}

	@RequestMapping(value = "/save", method = RequestMethod.POST, params = "save")
	public ModelAndView saveUser(@ModelAttribute("regForm") @Valid final RegisterForm form, final BindingResult binding) {
		ModelAndView res;
		if (binding.hasErrors())
			return this.createActorEditModelAndView(form);
		else
			try {
				Assert.isTrue(form.getForm().getRole() != null && form.getForm().getRole() != "", "Error registering: no role specified");
				//admin stuff

				//member stuff
				if (form.getForm().getRole().equals(Authority.MEMBER))
					this.memberService.register(form);
				res = new ModelAndView("redirect:../security/login.do");
			} catch (

			final Exception e) {
				res = this.createActorEditModelAndView(form, "register.error");
			}
		return res;
	}

	// AUX // ============================================ //
	protected ModelAndView createActorEditModelAndView(final RegisterForm regForm) {
		return this.createActorEditModelAndView(regForm, null);
	}

	protected ModelAndView createActorEditModelAndView(final RegisterForm regForm, final String messageCode) {
		ModelAndView result;

		result = new ModelAndView("security/register");
		result.addObject("regForm", regForm);
		result.addObject("message", messageCode);
		return result;
	}

}
