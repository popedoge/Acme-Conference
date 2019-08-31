
package controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.ConferenceService;
import services.RegistrationService;
import domain.Conference;
import domain.Registration;

@Controller
@RequestMapping("/registration")
public class RegistrationController extends AbstractController {

	// TODO: MAKES CREDITCARDS EMBEDABBLE
	// TODO: views
	@Autowired
	private ActorService		actorService;
	@Autowired
	private RegistrationService	registrationService;
	@Autowired
	private ConferenceService	conferenceService;


	// TODO: finish edit -> has to save to registration
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam(required = false) final Integer id) {
		final Conference conference = this.conferenceService.findById(id);
		final Registration reg = this.registrationService.create(conference);
		return this.createEditModelAndView(reg);
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@ModelAttribute("registration") @Valid final Registration reg, final BindingResult binding) {
		ModelAndView res;
		if (binding.hasErrors())
			res = this.createEditModelAndView(reg);
		else
			try {
				this.registrationService.save(reg);
				res = new ModelAndView("redirect:list.do");
			} catch (final Exception e) {
				res = this.createEditModelAndView(reg, "preferences.error");
			}
		return res;
	}

	// protected
	protected ModelAndView createEditModelAndView(final Registration reg) {
		return this.createEditModelAndView(reg, null);
	}

	protected ModelAndView createEditModelAndView(final Registration reg, final String messageCode) {
		ModelAndView result;

		result = new ModelAndView("card/edit");
		result.addObject("Registration", reg);
		result.addObject("message", messageCode);
		return result;
	}

}
