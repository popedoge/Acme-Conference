
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

import domain.Section;
import forms.SectionForm;
import services.SectionService;
import services.TutorialService;

@Controller
@RequestMapping("/section")
public class SectionController extends AbstractController {

	@Autowired
	private SectionService	sectionService;
	@Autowired
	private TutorialService	tutorialService;


	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam final Integer id) {
		final Section section = this.sectionService.create();
		final SectionForm form = new SectionForm();
		form.setTutorialId(id);
		form.setSection(section);
		return this.createEditModelAndView(form);
	}
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final Integer tutorialId, @RequestParam final Integer sectionId) {
		final Section section = this.sectionService.findById(sectionId);
		final SectionForm form = new SectionForm();
		form.setTutorialId(tutorialId);
		form.setSection(section);
		return this.createEditModelAndView(form);
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final SectionForm form, final BindingResult binding, final RedirectAttributes attributes) {
		ModelAndView res;
		if (binding.hasErrors())
			res = this.createEditModelAndView(form);
		else
			try {
				this.tutorialService.saveAndAdd(form);

				res = new ModelAndView("redirect:/activity/view.do?id=" + form.getTutorialId());
				attributes.addFlashAttribute("notif", "conference.section.sent");
			} catch (final Exception e) {
				res = this.createEditModelAndView(form, "section.error");
			}
		return res;
	}

	// protected
	protected ModelAndView createEditModelAndView(final SectionForm section) {
		return this.createEditModelAndView(section, null);
	}

	protected ModelAndView createEditModelAndView(final SectionForm section, final String messageCode) {
		ModelAndView result;

		result = new ModelAndView("activity/section/edit");
		result.addObject("section", section);
		result.addObject("message", messageCode);
		return result;
	}
}
