
package controllers;

import java.util.ArrayList;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import domain.Actor;
import domain.Conference;
import domain.Reviewer;
import domain.Submission;
import services.ActorService;
import services.AdminService;
import services.ConferenceService;
import services.ReviewerService;
import services.SubmissionService;

@Controller
@RequestMapping("/submission")
public class SubmissionController extends AbstractController {

	@Autowired
	private SubmissionService	submissionService;
	@Autowired
	private ActorService		actorService;
	@Autowired
	private ConferenceService	conferenceService;
	@Autowired
	private ReviewerService		reviewerService;
	@Autowired
	private AdminService		adminService;


	@RequestMapping(value = "/admin/autoassign", method = RequestMethod.GET)
	public ModelAndView autoAssign(final RedirectAttributes attributes) {
		final ModelAndView res = new ModelAndView("redirect:list.do");
		this.adminService.autoAssign();
		attributes.addFlashAttribute("notif", "submission.autoassign");
		return res;
	}

	@RequestMapping(value = "/admin/reviewer/list", method = RequestMethod.GET)
	public ModelAndView listReviewers(@RequestParam final Integer submissionId) {
		ModelAndView res;
		final Submission submission = this.submissionService.findById(submissionId);
		final java.util.List<Reviewer> allReviewers = this.reviewerService.findAll();
		res = new ModelAndView("reviewer/list");
		res.addObject("submission", submission);
		res.addObject("reviewers", submission.getReviewers());
		res.addObject("allReviewers", allReviewers);
		return res;
	}

	@RequestMapping(value = "/admin/reviewer/assign", method = RequestMethod.GET)
	public ModelAndView assignReviewer(@RequestParam final Integer submissionId, @RequestParam final Integer reviewerId, final RedirectAttributes attributes) {
		final ModelAndView res;
		final Submission submission = this.submissionService.findById(submissionId);
		final Reviewer reviewer = this.reviewerService.findById(reviewerId);
		this.submissionService.assignReviewer(submission, reviewer);
		res = new ModelAndView("redirect:list.do?submissionId=" + submission.getId());
		return res;
	}

	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public ModelAndView view(@RequestParam(required = false) final Integer id) {
		ModelAndView res;
		final Submission submission = this.submissionService.findById(id);
		res = new ModelAndView("submission/view");
		res.addObject("submission", submission);
		return res;
	}
	@RequestMapping(value = "/author/list", method = RequestMethod.GET)
	public ModelAndView ListByAuthor() {
		ModelAndView res;
		final Actor actor = this.actorService.findPrincipal();
		final java.util.List<Submission> submissions = this.submissionService.findSubmissionByOwner(actor);
		res = new ModelAndView("submission/list");
		res.addObject("submissions", submissions);
		return res;
	}

	@RequestMapping(value = "/admin/list", method = RequestMethod.GET)
	public ModelAndView ListAll() {
		ModelAndView res;
		this.adminService.findPrincipal();
		final java.util.List<Submission> submissions = this.submissionService.findAll();
		res = new ModelAndView("submission/list");
		res.addObject("submissions", submissions);
		return res;
	}

	@RequestMapping(value = "/reviewer/list", method = RequestMethod.GET)
	public ModelAndView List(@RequestParam(required = false) final Integer id) {
		ModelAndView res;
		final Reviewer reviewer = this.reviewerService.findPrincipal();
		java.util.List<Submission> submissions = new ArrayList<>();
		if (id != null && id != 0) {
			final Conference conference = this.conferenceService.findById(id);
			submissions = this.submissionService.findByReviewerAndConference(reviewer, conference);
		} else
			submissions = this.submissionService.findByReviewer(reviewer);
		res = new ModelAndView("submission/list");
		res.addObject("submissions", submissions);
		return res;
	}

	@RequestMapping(value = "/author/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam final Integer id) {

		final Submission submission = this.submissionService.initialize(id);
		return this.createEditModelAndView(submission);
	}
	@RequestMapping(value = "/author/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final Integer id) {

		final Submission submission = this.submissionService.findById(id);
		return this.createEditModelAndView(submission);
	}

	@RequestMapping(value = "/author/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Submission submission, final BindingResult binding, final RedirectAttributes attributes) {
		ModelAndView res;
		Assert.isTrue(submission.getConference().getSubmissionDL().after(new Date()));
		if (binding.hasErrors())
			res = this.createEditModelAndView(submission);
		else
			try {
				this.submissionService.save(submission);

				res = new ModelAndView("redirect:/conference/view.do?id=" + submission.getConference().getId());
				attributes.addFlashAttribute("notif", "conference.submission.sent");
			} catch (final Exception e) {
				res = this.createEditModelAndView(submission, "preferences.error");
			}
		return res;
	}

	// protected
	protected ModelAndView createEditModelAndView(final Submission submission) {
		return this.createEditModelAndView(submission, null);
	}

	protected ModelAndView createEditModelAndView(final Submission submission, final String messageCode) {
		ModelAndView result;

		result = new ModelAndView("submission/edit");
		result.addObject("submission", submission);
		result.addObject("message", messageCode);
		return result;
	}
}
