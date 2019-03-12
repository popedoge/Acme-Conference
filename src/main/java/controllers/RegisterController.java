
package controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.MemberService;
import domain.Member;
import forms.RegisterForm;

@Controller
@RequestMapping("/register")
public class RegisterController {

	@Autowired
	private MemberService	memberService;
	@Autowired
	private ActorService	actorService;


	@RequestMapping(value = "/init", method = RequestMethod.GET)
	public ModelAndView createUser() {
		final Member member = this.memberService.create();
		final RegisterForm regForm = new RegisterForm();
		regForm.setRole("MEMBER");
		regForm.setForm(this.actorService.formatForm(member));
		return this.createActorEditModelAndView(regForm);
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ModelAndView saveUser(@ModelAttribute("regForm") @Valid final RegisterForm form, final BindingResult binding) {
		ModelAndView res;
		if (binding.hasErrors())
			return this.createActorEditModelAndView(form);
		else
			try {
				this.actorService.register(form);
				res = new ModelAndView("redirect:../security/login.do");
			} catch (final Exception e) {
				res = this.createActorEditModelAndView(form, "register.error");
			}
		return res;
	}
	//AUX // ============================================ //
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
