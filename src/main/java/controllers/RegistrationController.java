
package controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import domain.Conference;
import domain.Registration;
import services.ActorService;
import services.ConferenceService;
import services.RegistrationService;

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
	public ModelAndView edit(@RequestParam(required = false) final Integer id, final RedirectAttributes attributes) {
		ModelAndView res = new ModelAndView();
		try {
			final Conference conference = this.conferenceService.findById(id);
			final Registration registration = this.registrationService.create(conference);
			res = this.createEditModelAndView(registration);
		} catch (final Exception e) {
			res = new ModelAndView("redirect:/conference/view.do?id=" + id);
			attributes.addFlashAttribute("notif", "conference.registered");
		}
		return res;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Registration registration, final BindingResult binding) {
		ModelAndView res;
		if (binding.hasErrors())
			res = this.createEditModelAndView(registration);
		else
			try {
				this.registrationService.save(registration);

				res = new ModelAndView("redirect:/conference/view.do?id=" + registration.getConference().getId());
			} catch (final Exception e) {
				res = this.createEditModelAndView(registration, "preferences.error");
			}
		return res;
	}

	// protected
	protected ModelAndView createEditModelAndView(final Registration registration) {
		return this.createEditModelAndView(registration, null);
	}

	protected ModelAndView createEditModelAndView(final Registration registration, final String messageCode) {
		ModelAndView result;

		result = new ModelAndView("registration/edit");
		result.addObject("registration", registration);
		result.addObject("message", messageCode);
		return result;
	}

}
