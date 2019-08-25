package controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ConferenceService;
import domain.Conference;

@Controller
@RequestMapping("/conference")
public class ConferenceController extends AbstractController {

	@Autowired
	private ConferenceService conferenceService;

	// lists all or list all created by id
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView listAll(@RequestParam(required = false) final Integer id) {
		ModelAndView res;
		List<Conference> conferences = this.conferenceService.findAll();
		res = new ModelAndView("conference/list");
		res.addObject("conferences", conferences);
		return res;
	}

	@RequestMapping(value = "/admin/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam(required = false) final Integer id) {
		Conference conference;
		if (id != null && id != 0) {
			conference = this.conferenceService.findById(id);
		} else {
			conference = this.conferenceService.create();
		}
		return this.createEditModelAndView(conference);
	}

	@RequestMapping(value = "/admin/edit", method = RequestMethod.POST)
	public ModelAndView save(
			@ModelAttribute("conference") @Valid final Conference conference,
			final BindingResult binding) {
		ModelAndView res;
		if (binding.hasErrors())
			res = this.createEditModelAndView(conference);
		else
			try {
				this.conferenceService.save(conference);
				res = new ModelAndView("redirect:list.do");
			} catch (final Exception e) {
				res = this.createEditModelAndView(conference,
						"preferences.error");
			}
		return res;
	}

	// protected
	protected ModelAndView createEditModelAndView(final Conference conference) {
		return this.createEditModelAndView(conference, null);
	}

	protected ModelAndView createEditModelAndView(final Conference conference,
			final String messageCode) {
		ModelAndView result;

		result = new ModelAndView("conference/edit");
		result.addObject("conference", conference);
		result.addObject("message", messageCode);
		return result;
	}

}
