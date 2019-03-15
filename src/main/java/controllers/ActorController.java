
package controllers;

import java.util.ArrayList;
import java.util.List;

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
import services.SocialNetworkService;
import services.SocialProfileService;
import domain.Actor;
import domain.ActorPreferences;
import domain.SocialNetwork;
import domain.SocialProfile;
import forms.ActorForm;
import forms.RegisterForm;
import forms.SocialProfileForm;

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
	@Autowired
	private SocialProfileService	socialProfService;
	@Autowired
	private SocialNetworkService	socialNetService;


	//load actor profile
	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public ModelAndView userProfile(@RequestParam(required = false) final Integer id) {
		ModelAndView res;
		res = new ModelAndView("actor/profile");
		Actor actor;
		ActorForm form;
		ActorPreferences preferences;
		//if id==0 load principal's profile and give owner options
		if (id == null || id == 0) {
			actor = this.actorService.findPrincipal();
			preferences = this.preferencesService.findByPrincipal();
			form = this.actorService.formatForm(actor, preferences);
			res.addObject("owner", true);
		} else {
			actor = this.actorService.findOne(id);
			Assert.notNull(actor);
			preferences = this.preferencesService.findByActor(actor);
			form = this.actorService.formatForm(actor, preferences);
		}
		final List<SocialProfile> profiles = this.socialProfService.findByActor(actor);
		res.addObject("profiles", profiles);
		res.addObject("actor", form);
		return res;
	}

	//edit actor information
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit() {
		ModelAndView res;
		final ActorForm actorForm = this.actorService.formatForm(this.actorService.findPrincipal());
		res = this.createActorEditModelAndView(actorForm);
		return res;
	}
	//save actor information
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
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

	//edit/create social profile
	@RequestMapping(value = "/social/edit", method = RequestMethod.GET)
	public ModelAndView editSocialProfile(@RequestParam(required = false) final Integer id) {
		ModelAndView res;
		SocialProfile profile;
		if (id != null)
			profile = this.socialProfService.findById(id);
		else
			profile = this.socialProfService.create();
		final SocialProfileForm form = this.socialProfService.formatForm(profile);
		res = this.createSocialProfileEditModelAndView(form);
		return res;
	}

	@RequestMapping(value = "/social/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView saveSocialProfile(@ModelAttribute("form") @Valid final SocialProfileForm form, final BindingResult binding) {
		ModelAndView res;
		if (binding.hasErrors())
			res = this.createSocialProfileEditModelAndView(form);
		else
			try {
				final SocialProfile saved = this.socialProfService.save(this.socialProfService.parseForm(form));
				res = new ModelAndView("redirect:/profile.do");
			} catch (final Exception e) {
				res = this.createSocialProfileEditModelAndView(form, "socialprofile.error");
			}
		return res;
	}

	//delete social profile
	@RequestMapping(value = "/social/delete", method = RequestMethod.GET)
	public ModelAndView deleteSocialProfile(@RequestParam final int id) {
		this.socialProfService.delete(id);
		return new ModelAndView("redirect:/profile.do");
	}

	//AUX
	//social profile
	protected ModelAndView createSocialProfileEditModelAndView(final SocialProfileForm form) {
		return this.createSocialProfileEditModelAndView(form, null);
	}

	protected ModelAndView createSocialProfileEditModelAndView(final SocialProfileForm form, final String messageCode) {
		ModelAndView res;
		final List<SocialNetwork> networks = this.socialNetService.findAll();
		res = new ModelAndView("actor/social/edit");
		res.addObject("networks", networks);
		res.addObject("form", form);
		res.addObject("message", messageCode);
		return res;
	}

	//register
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

	//actor information
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
