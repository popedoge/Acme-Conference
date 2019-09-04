
package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/misc")
public class MiscController {

	@RequestMapping(value = "/terms", method = RequestMethod.GET)
	public ModelAndView terms() {
		final ModelAndView res = new ModelAndView("terms/terms");
		return res;
	}

}
