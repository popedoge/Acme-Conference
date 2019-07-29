package register;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import services.ActorService;
import services.AuthorService;
import services.SiteConfigurationService;
import utilities.AbstractTest;
import domain.Actor;
import domain.Author;
import forms.ActorForm;
import forms.RegisterForm;

@ContextConfiguration(locations = {"classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class ActorTest extends AbstractTest {

	@Autowired
	private AuthorService authorService;
	@Autowired
	private SiteConfigurationService siteConfigService;
	@Autowired
	private ActorService actorService;

	@Test
	public void testCreate() {
		Author saved;
		List<Author> authors;
		RegisterForm regForm = new RegisterForm();
		ActorForm actForm = new ActorForm();
		// datos
		regForm.setAcceptTerms(true);
		regForm.setPassword("test");
		regForm.setRole("AUTHOR");
		actForm.setAddress("test");
		actForm.setEmail("test@acme.com");
		actForm.setFirstName("test");
		actForm.setLastName("test");
		actForm.setPhoneNumber("4444");
		actForm.setPhoto("https://66.media.tumblr.com/403952a5f76181fa94b78d6318dadff8/cb88b6bbf0ceb7e8-5a/s540x810/1872472fefbf14f611326c0d1ce8e9d86ceb9040.jpg");
		actForm.setUsername("test55");
		regForm.setForm(actForm);

		saved = this.authorService.register(regForm);

		authors = this.authorService.findAll();
		Assert.isTrue(authors.contains(saved));
	}

	@Test
	public void testPhoneFormat() {
		Author saved;
		List<Author> authors;
		RegisterForm regForm = new RegisterForm();
		ActorForm actForm = new ActorForm();
		// datos
		regForm.setAcceptTerms(true);
		regForm.setPassword("test");
		regForm.setRole("AUTHOR");
		actForm.setAddress("test");
		actForm.setEmail("test@acme.com");
		actForm.setFirstName("test");
		actForm.setLastName("test");
		actForm.setPhoneNumber("4444");
		actForm.setPhoto("https://66.media.tumblr.com/403952a5f76181fa94b78d6318dadff8/cb88b6bbf0ceb7e8-5a/s540x810/1872472fefbf14f611326c0d1ce8e9d86ceb9040.jpg");
		actForm.setUsername("test55");
		regForm.setForm(actForm);

		saved = this.authorService.register(regForm);
		String cc = "+"
				+ String.valueOf(this.siteConfigService.find().getCountryCode());
		String phonenum = saved.getPhoneNumber();

		Assert.isTrue(phonenum.startsWith(cc));
	}

	@Test
	public void testEditActor() {
		Author saved;
		List<Author> authors;
		RegisterForm regForm = new RegisterForm();
		ActorForm actForm = new ActorForm();
		// datos
		regForm.setAcceptTerms(true);
		regForm.setPassword("test");
		regForm.setRole("AUTHOR");
		actForm.setAddress("test");
		actForm.setEmail("test@acme.com");
		actForm.setFirstName("test");
		actForm.setLastName("test");
		actForm.setPhoneNumber("4444");
		actForm.setPhoto("https://66.media.tumblr.com/403952a5f76181fa94b78d6318dadff8/cb88b6bbf0ceb7e8-5a/s540x810/1872472fefbf14f611326c0d1ce8e9d86ceb9040.jpg");
		actForm.setUsername("test55");
		regForm.setForm(actForm);

		saved = this.authorService.register(regForm);

		ActorForm editForm = this.actorService.formatForm(saved);
		editForm.setPhoneNumber("5555");
		editForm.setFirstName("edit test");
		Actor actor = this.actorService.parseForm(editForm);
		Actor savedActor = this.actorService.save(actor);

		Assert.isTrue(this.actorService.findById(savedActor.getId())
				.getPhoneNumber().equals("5555"));
	}

}
