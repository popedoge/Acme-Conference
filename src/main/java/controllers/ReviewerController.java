package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ReviewerRequestService;

@Controller
@RequestMapping("/reviewer")
public class ReviewerController {

	@Autowired
	private ReviewerRequestService revreqservice;

	// list requests to become reviewer (for admin only)
	@RequestMapping(value = "/admin/requests/list", method = RequestMethod.GET)
	public ModelAndView listRequests() {
		ModelAndView res;
		return res;
	}

	@RequestMapping(value = "/admin/requests/accept", method = RequestMethod.GET)
	public ModelAndView acceptRequest(@RequestParam final Integer id) {
		ModelAndView res;
		return res;
	}

	@RequestMapping(value = "/admin/requests/deny", method = RequestMethod.GET)
	public ModelAndView denyRequest(@RequestParam final Integer id) {
		ModelAndView res;
		return res;
	}
}
