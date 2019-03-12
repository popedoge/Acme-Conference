
package controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.ActorPreferencesService;
import domain.ActorPreferences;
import forms.PreferencesForm;

@Controller
@RequestMapping("/user/settings")
public class PreferencesController {

	@Autowired
	private ActorPreferencesService	preferencesService;


	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit() {
		final ActorPreferences preferences = this.preferencesService.findByPrincipal();
		return this.createEditModelAndView(this.preferencesService.formatForm(preferences));
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public ModelAndView save(@ModelAttribute("form") @Valid final PreferencesForm form, final BindingResult binding) {
		ModelAndView res;
		if (binding.hasErrors())
			res = this.createEditModelAndView(form);
		else
			try {
				final ActorPreferences preferences = this.preferencesService.parseForm(form);
				this.preferencesService.save(preferences);
				res = this.createEditModelAndView(form, "preferences.saved");
			} catch (final Exception e) {
				res = this.createEditModelAndView(form, "preferences.error");
			}
		return res;
	}

	protected ModelAndView createEditModelAndView(final PreferencesForm form) {
		return this.createEditModelAndView(form, null);
	}

	protected ModelAndView createEditModelAndView(final PreferencesForm form, final String messageCode) {
		ModelAndView result;

		result = new ModelAndView("actor/preferences");
		result.addObject("form", form);
		result.addObject("message", messageCode);
		return result;
	}
}
