package controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.ConferenceService;
import services.SubmissionService;
import domain.Actor;
import domain.Conference;
import domain.Submission;

@Controller
@RequestMapping("/submission")
public class SubmissionController extends AbstractController {

	@Autowired
	private SubmissionService submissionService;
	@Autowired
	private ActorService actorService;
	@Autowired
	private ConferenceService conferenceService;

	@RequestMapping(value = "/author/list", method = RequestMethod.GET)
	public ModelAndView ListByAuthor() {
		ModelAndView res;
		Actor actor = this.actorService.findPrincipal();
		java.util.List<Submission> submissions = this.submissionService
				.findSubmissionByOwner(actor);
		res = new ModelAndView();
		res.addObject("submissions", submissions);
		return res;
	}
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView List(
			@RequestParam(required = false) final Integer conferenceId) {
		ModelAndView res;
		java.util.List<Submission> submissions = new ArrayList<>();
		if (conferenceId != null && conferenceId != 0) {
			Conference conference = this.conferenceService
					.findById(conferenceId);
			submissions = this.submissionService.findByConference(conference);
		} else {
			submissions = this.submissionService.findAll();
		}
		res = new ModelAndView();
		res.addObject("submissions", submissions);
		return res;
	}
}
