package services;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Submission;

@ContextConfiguration(locations = {"classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class SubmissionTests extends AbstractTest {

	@Autowired
	private ActorService actorService;
	@Autowired
	private SubmissionService submissionService;
	@Autowired
	private ConferenceService conferenceService;

	@Test
	public void testTicker() {
		super.authenticate("admin1");
		Submission submission = this.submissionService.initialize(126);

		Assert.isTrue(submission.getTicker().matches("^[A-Z]{3,}-\\w{4,}$"),
				submission.getTicker());
	}
}
