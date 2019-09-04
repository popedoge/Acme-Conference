
package controllers;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import domain.Activity;
import domain.Conference;
import domain.Registration;
import forms.ConferenceOptionForm;
import services.ActivityService;
import services.ConferenceService;
import services.RegistrationService;

@Controller
@RequestMapping("/conference")
public class ConferenceController extends AbstractController {

	//TODO: section edit
	@Autowired
	private ConferenceService	conferenceService;
	@Autowired
	private RegistrationService	registrationService;
	@Autowired
	private ActivityService		activityService;


	@RequestMapping(value = "/admin/lock", method = RequestMethod.GET)
	public ModelAndView lock(@RequestParam final Integer id, final RedirectAttributes attributes) {
		ModelAndView res;
		this.conferenceService.lock(id);
		res = new ModelAndView("redirect:/conference/admin/list.do");
		return res;
	}

	@RequestMapping(value = "/admin/eval", method = RequestMethod.GET)
	public ModelAndView evaluate(@RequestParam final Integer id, final RedirectAttributes attributes) {
		ModelAndView res;
		res = new ModelAndView("redirect:/conference/view?id=" + id);
		this.conferenceService.evaluate(this.conferenceService.findById(id));
		attributes.addFlashAttribute("notif", "conference.eval.complete");
		return res;
	}

	@RequestMapping(value = "/admin/delete", method = RequestMethod.GET)
	public ModelAndView evaluate(@RequestParam final Integer id) {
		ModelAndView res;
		this.conferenceService.delete(id);
		res = new ModelAndView("redirect:/conference/list.do");
		return res;
	}

	// TODO: /view
	// TODO: tiles
	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public ModelAndView view(@RequestParam final Integer id) {
		ModelAndView res;
		final Conference conference = this.conferenceService.findById(id);
		final List<Registration> attendees = this.registrationService.findByConference(id);
		final List<Activity> activities = this.activityService.findByConference(id);
		final ConferenceOptionForm options = this.conferenceService.options(conference);

		res = new ModelAndView("conference/view");
		res.addObject("activities", activities);
		res.addObject("conference", conference);
		res.addObject("attendees", attendees);
		res.addObject("options", options);
		return res;
	}
	//list all
	@RequestMapping(value = "/admin/list", method = RequestMethod.GET)
	public ModelAndView listAll() {
		ModelAndView res;
		final List<Conference> conferences = this.conferenceService.findAll();
		res = new ModelAndView("conference/list");
		res.addObject("conferences", conferences);
		return res;
	}

	// lists final
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView listFinal() {
		ModelAndView res;
		final List<Conference> conferences = this.conferenceService.findFinal();
		res = new ModelAndView("conference/list");
		res.addObject("conferences", conferences);
		return res;
	}

	@RequestMapping(value = "/admin/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam(required = false) final Integer id) {
		Conference conference;
		if (id != null && id != 0) {
			conference = this.conferenceService.findById(id);
			Assert.isTrue(!conference.getLocked());
		} else
			conference = this.conferenceService.create();
		return this.createEditModelAndView(conference);
	}

	@RequestMapping(value = "/admin/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@ModelAttribute("conference") @Valid final Conference conference, final BindingResult binding) {
		ModelAndView res;
		Assert.isTrue(!conference.getLocked());
		if (binding.hasErrors())
			res = this.createEditModelAndView(conference);
		else
			try {
				this.conferenceService.save(conference);
				res = new ModelAndView("redirect:/conference/admin/list.do");
			} catch (final Exception e) {
				res = this.createEditModelAndView(conference, "preferences.error");
			}
		return res;
	}

	// protected
	protected ModelAndView createEditModelAndView(final Conference conference) {
		return this.createEditModelAndView(conference, null);
	}

	protected ModelAndView createEditModelAndView(final Conference conference, final String messageCode) {
		ModelAndView result;

		result = new ModelAndView("conference/edit");
		result.addObject("conference", conference);
		result.addObject("message", messageCode);
		return result;
	}

}
