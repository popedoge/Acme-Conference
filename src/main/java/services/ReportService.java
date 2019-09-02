
package services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.Conference;
import domain.Report;
import domain.Reviewer;
import domain.Submission;
import repositories.ReportRepository;

@Service
@Transactional
public class ReportService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private ReportRepository	reportRepo;

	// Supporting services ----------------------------------------------------

	@Autowired
	private ActorService		actorService;
	@Autowired
	private ReviewerService		reviewerService;


	public Report create(final Submission submission) {
		final Report res = new Report();
		final Reviewer reviewer = this.reviewerService.findPrincipal();
		res.setOwner(reviewer);
		res.setSubmission(submission);
		return res;
	}

	public Report save(final Report report) {
		return this.reportRepo.save(report);
	}

	public void delete(final int id) {
		this.reportRepo.delete(id);
	}

	public Report findById(final int id) {
		return this.reportRepo.findOne(id);
	}

	public List<Report> findUnusedByUser(final Conference conference) {
		final List<Report> res = new ArrayList<>();
		return res;
	}

	public List<Report> findByOwner(final int id) {
		return this.reportRepo.findByOwner(id);
	}

	public List<Report> findBySubmission(final int id) {
		return this.reportRepo.findBySubmission(id);
	}
}
