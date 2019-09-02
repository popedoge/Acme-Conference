
package controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import domain.Report;
import domain.Submission;
import services.ReportService;
import services.ReviewerService;
import services.SubmissionService;

@Controller
@RequestMapping("/report")
public class ReportController extends AbstractController {

	@Autowired
	private ReportService		reportService;
	@Autowired
	private SubmissionService	submissionService;
	@Autowired
	private ReviewerService		reviewerService;


	@RequestMapping(value = "/reviewer/list", method = RequestMethod.GET)
	public ModelAndView list() {
		final ModelAndView res;
		final List<Report> reports = this.reportService.findByOwner(this.reviewerService.findPrincipal().getId());
		res = new ModelAndView("report/list");
		res.addObject("reports", reports);
		return res;
	}

	@RequestMapping(value = "/author/list", method = RequestMethod.GET)
	public ModelAndView listBySubmission(@RequestParam(required = false) final Integer id) {
		final ModelAndView res;
		final List<Report> reports = this.reportService.findBySubmission(id);
		res = new ModelAndView("report/list");
		res.addObject("reports", reports);
		return res;
	}

	// TODO: finish edit -> has to save to report
	@RequestMapping(value = "/reviewer/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam(required = false) final Integer id, final RedirectAttributes attributes) {
		ModelAndView res = new ModelAndView();
		final Submission submission = this.submissionService.findById(id);
		final Report report = this.reportService.create(submission);

		res = this.createEditModelAndView(report);
		return res;
	}

	@RequestMapping(value = "/reviewer/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Report report, final BindingResult binding) {
		ModelAndView res;
		if (binding.hasErrors())
			res = this.createEditModelAndView(report);
		else
			try {
				this.reportService.save(report);

				res = new ModelAndView("redirect:/submission/reviewer/list.do?id=" + report.getSubmission().getConference().getId());
			} catch (final Exception e) {
				res = this.createEditModelAndView(report, "preferences.error");
			}
		return res;
	}

	// protected
	protected ModelAndView createEditModelAndView(final Report report) {
		return this.createEditModelAndView(report, null);
	}

	protected ModelAndView createEditModelAndView(final Report report, final String messageCode) {
		ModelAndView result;
		final List<String> decisionList = report.returnDecisonList();
		result = new ModelAndView("report/edit");
		result.addObject("decisionList", decisionList);
		result.addObject("report", report);
		result.addObject("message", messageCode);
		return result;
	}

}
