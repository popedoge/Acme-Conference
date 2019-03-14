
package controllers;

import java.util.ArrayList;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActorPreferencesService;
import services.ActorService;
import services.AdminService;
import services.MemberService;
import domain.Actor;
import domain.ActorPreferences;
import forms.ActorForm;
import forms.RegisterForm;

@Controller
@RequestMapping("/actor")
public class ActorController extends AbstractController {

	@Autowired
	private MemberService			memberService;
	@Autowired
	private ActorService			actorService;
	@Autowired
	private AdminService			adminService;
	@Autowired
	private ActorPreferencesService	preferencesService;


	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public ModelAndView userProfile(@RequestParam(required = false) final Integer id) {
		ModelAndView res;
		res = new ModelAndView("actor/profile");
		Actor actor;
		ActorForm form;
		ActorPreferences preferences;
		if (id == null || id == 0) {
			actor = this.actorService.findPrincipal();
			preferences = this.preferencesService.findByPrincipal();
			form = this.actorService.formatForm(actor, preferences);
		} else {
			actor = this.actorService.findOne(id);
			Assert.notNull(actor);
			preferences = this.preferencesService.findByActor(actor);
			form = this.actorService.formatForm(actor, preferences);
		}
		res.addObject("actor", form);
		return res;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit() {
		ModelAndView res;
		final ActorForm actorForm = this.actorService.formatForm(this.actorService.findPrincipal());
		res = this.createActorEditModelAndView(actorForm);
		return res;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public ModelAndView commit(@ModelAttribute("actorForm") @Valid final ActorForm actorForm, final BindingResult binding) {
		ModelAndView res;
		if (binding.hasErrors())
			res = this.createActorEditModelAndView(actorForm);
		else
			try {
				this.actorService.save(this.actorService.parseForm(actorForm));
				res = new ModelAndView("redirect:profile.do");
			} catch (final Exception e) {
				res = this.createActorEditModelAndView(actorForm, "actor.commit.error");
			}
		return res;
	}

	//AUX
	protected ModelAndView createEditModelAndView(final RegisterForm registerForm) {
		return this.createEditModelAndView(registerForm, null);
	}

	protected ModelAndView createEditModelAndView(final RegisterForm registerForm, final String messageCode) {
		ModelAndView result;
		final ArrayList<String> roles = new ArrayList<>();
		roles.add("MEMBER");
		roles.add("ADMIN");

		result = new ModelAndView("security/register");
		result.addObject("regForm", registerForm);
		result.addObject("roles", roles);
		result.addObject("message", messageCode);
		return result;
	}

	protected ModelAndView createActorEditModelAndView(final ActorForm actorForm) {
		return this.createActorEditModelAndView(actorForm, null);
	}

	protected ModelAndView createActorEditModelAndView(final ActorForm actorForm, final String messageCode) {
		ModelAndView result;

		result = new ModelAndView("actor/edit");
		result.addObject("actorForm", actorForm);
		result.addObject("message", messageCode);
		return result;
	}
}
