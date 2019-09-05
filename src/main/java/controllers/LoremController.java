
package controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import domain.Lorem;
import services.AdminService;
import services.ConferenceService;
import services.LoremService;

@Controller
@RequestMapping("/lorem")
public class LoremController extends AbstractController {

	@Autowired
	private LoremService		loremService;
	@Autowired
	private AdminService		adminService;
	@Autowired
	private ConferenceService	referenceService;


	@RequestMapping(value = "/admin/lock", method = RequestMethod.GET)
	public ModelAndView lock(@RequestParam(required = false) final Integer id) {
		//assert admin
		this.adminService.findPrincipal();

		this.loremService.lock(id);
		return new ModelAndView("redirect:list.do");
	}

	//delete
	//TODO: test this
	@RequestMapping(value = "/admin/delete", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam final Integer id) {
		//assert admin
		this.adminService.findPrincipal();

		this.loremService.delete(id);
		return new ModelAndView("redirect:list.do");
	}

	//list all - final and draft
	@RequestMapping(value = "/admin/list", method = RequestMethod.GET)
	public ModelAndView listAll() {
		//assert admin
		this.adminService.findPrincipal();

		final ModelAndView res;
		final List<Lorem> lorems = this.loremService.findAll();
		res = new ModelAndView("lorem/list");
		res.addObject("lorem", lorems);
		return res;
	}

	//list final
	//if id present -> list by reference
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView listFinal(@RequestParam(required = false) final Integer id) {
		final ModelAndView res;
		List<Lorem> lorems = new ArrayList<>();
		if (id != null)
			lorems = this.loremService.findFinalByReference(id);
		else
			lorems = this.loremService.findFinal();
		res = new ModelAndView("lorem/list");
		res.addObject("lorem", lorems);
		return res;
	}

	//edit
	@RequestMapping(value = "/admin/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam(required = false) final Integer id, @RequestParam(required = false) final Integer referenceId) {
		//assert admin
		this.adminService.findPrincipal();

		ModelAndView res;
		Lorem lorem;
		if (id != null) {
			//id present -> find lorem
			lorem = this.loremService.findById(id);
			Assert.isTrue(!lorem.getLocked());
		} else {
			lorem = this.loremService.create();
			lorem.setReference(this.referenceService.findById(referenceId));
		}

		res = this.createEditModelAndView(lorem);
		return res;
	}

	//save
	@RequestMapping(value = "/admin/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Lorem lorem, final BindingResult binding) {
		ModelAndView res;
		if (binding.hasErrors())
			res = this.createEditModelAndView(lorem);
		else
			try {
				this.loremService.save(lorem);

				res = new ModelAndView("redirect:list.do");
			} catch (final Exception e) {
				res = this.createEditModelAndView(lorem, "lorem.error");
			}
		return res;
	}

	// protected
	protected ModelAndView createEditModelAndView(final Lorem lorem) {
		return this.createEditModelAndView(lorem, null);
	}

	protected ModelAndView createEditModelAndView(final Lorem lorem, final String messageCode) {
		ModelAndView result;
		result = new ModelAndView("lorem/edit");
		result.addObject("lorem", lorem);
		result.addObject("message", messageCode);
		return result;
	}

}
