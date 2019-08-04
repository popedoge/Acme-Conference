package register;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import services.ReviewerService;
import domain.Reviewer;
import forms.ActorForm;
import forms.RegisterForm;

@ContextConfiguration(locations = {"classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class ReviewerTest {

	@Autowired
	private ReviewerService reviewerService;

	@Test
	public void testCreate() {
		Reviewer saved;
		List<Reviewer> reviewers;
		RegisterForm regForm = new RegisterForm();
		ActorForm actForm = new ActorForm();
		// datos
		regForm.setAcceptTerms(true);
		regForm.setPassword("test");

		actForm.setAddress("test");
		actForm.setEmail("test@acme.com");
		actForm.setFirstName("test");
		actForm.setLastName("test");
		actForm.setPhoneNumber("4444");
		actForm.setPhoto("https://66.media.tumblr.com/403952a5f76181fa94b78d6318dadff8/cb88b6bbf0ceb7e8-5a/s540x810/1872472fefbf14f611326c0d1ce8e9d86ceb9040.jpg");
		actForm.setUsername("test55");
		actForm.setExpertise("test,test");
		regForm.setForm(actForm);
		regForm.getForm().setRole("REVIEWER");
		saved = this.reviewerService.register(regForm);

		reviewers = this.reviewerService.findAll();
		Assert.isTrue(reviewers.contains(saved));
	}
}
