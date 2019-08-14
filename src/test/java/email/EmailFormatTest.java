package email;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import services.ActorService;
import utilities.AbstractTest;
import domain.Actor;
import forms.ActorForm;

@ContextConfiguration(locations = {"classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class EmailFormatTest extends AbstractTest {

	@Autowired
	private ActorService actorService;

	@Test
	public void testFormat() {
		super.authenticate("admin1");
		ActorForm form = this.actorService.formatForm(this.actorService
				.findPrincipal());
		String email = "thisfails";
		form.setEmail(email);
		Actor actor = this.actorService.parseForm(form);
		Actor saved = this.actorService.save(actor);
		Assert.isTrue(saved.getEmail().equals(email));
	}

}
