
package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import domain.Activity;
import forms.ActivityForm;
import services.ActivityService;
import services.ConferenceService;

@Controller
@RequestMapping("/activity")
public class ActivityController extends AbstractController {

	@Autowired
	private ConferenceService	conferenceService;
	@Autowired
	private ActivityService		activityService;


	//TODO: create activities
	//TODO: activity views: view & edit
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam final Integer conferenceId, @RequestParam final Integer type) {
		final ModelAndView res;
		final Activity activity = this.activityService.create();
		final ActivityForm form = this.activityService.formatForm(activity, this.conferenceService.findById(conferenceId), type);
		res = this.createEditModelAndView(form);
		return res;
	}

	// protected
	protected ModelAndView createEditModelAndView(final ActivityForm form) {
		return this.createEditModelAndView(form, null);
	}

	protected ModelAndView createEditModelAndView(final ActivityForm form, final String messageCode) {
		ModelAndView result;
		result = new ModelAndView("activity/edit");
		result.addObject("activity", form);
		result.addObject("message", messageCode);
		return result;
	}

}
