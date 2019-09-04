
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

import domain.Activity;
import forms.ActivityForm;
import services.ActivityService;
import services.ConferenceService;
import services.SubmissionService;

@Controller
@RequestMapping("/activity")
public class ActivityController extends AbstractController {

	@Autowired
	private ConferenceService	conferenceService;
	@Autowired
	private ActivityService		activityService;
	@Autowired
	private SubmissionService	submissionService;


	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public ModelAndView view(@RequestParam final Integer id, @RequestParam final Integer conferenceId) {
		final ModelAndView res;
		final Activity activity = this.activityService.findById(id);
		final ActivityForm form = this.activityService.formatForm(activity, conferenceId, this.activityService.findType(activity));
		res = new ModelAndView("activity/view");
		res.addObject("activity", form);
		return res;
	}

	//TODO: add sections
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam final Integer conferenceId, @RequestParam final Integer id) {
		ModelAndView res;
		this.activityService.delete(id);
		res = new ModelAndView("redirect:/conference/view.do?id=" + conferenceId);
		return res;
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam final Integer conferenceId, @RequestParam final Integer type) {
		final ModelAndView res;
		final Activity activity = this.activityService.create();
		final ActivityForm form = this.activityService.formatForm(activity, conferenceId, type);
		res = this.createEditModelAndView(form);
		return res;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final Integer id, @RequestParam final Integer conferenceId) {
		final ModelAndView res;
		final Activity activity = this.activityService.findById(id);
		final ActivityForm form = this.activityService.formatForm(activity, conferenceId, null);
		res = this.createEditModelAndView(form);
		return res;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@ModelAttribute("activity") @Valid final ActivityForm form, final BindingResult binding) {
		ModelAndView res;
		if (binding.hasErrors())
			res = this.createEditModelAndView(form);
		else
			//TODO: fix activity save
			try {
				this.activityService.save(form);
				res = new ModelAndView("redirect:/conference/view.do?id=" + form.getConferenceId());
			} catch (final Exception e) {
				res = this.createEditModelAndView(form, "activity.error");
			}
		return res;
	}

	// protected
	protected ModelAndView createEditModelAndView(final ActivityForm form) {
		return this.createEditModelAndView(form, null);
	}

	protected ModelAndView createEditModelAndView(final ActivityForm form, final String messageCode) {
		ModelAndView result;
		result = new ModelAndView("activity/edit");
		if (form.getType() == 1)
			result.addObject("submissions", this.submissionService.findByConference(this.conferenceService.findById(form.getConferenceId())));

		result.addObject("activity", form);
		result.addObject("message", messageCode);
		return result;
	}

}
