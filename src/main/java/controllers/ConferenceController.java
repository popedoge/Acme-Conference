package controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import domain.Conference;
import services.ActorService;
import services.ConferenceService;

@Controller
@RequestMapping("/conference")
public class ConferenceController extends AbstractController {

	@Autowired
	private ConferenceService		conferenceService;
	@Autowired
	private ActorService			actorService;
//	@Autowired
//	private AdminService			adminService;
//	@Autowired
//	private ActorPreferencesService	preferencesService;
//	@Autowired
//	private SocialProfileService	socialProfService;
//	@Autowired
//	private SocialNetworkService	socialNetService;
	
	
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView listAll() {
				
		ModelAndView res;
		
		List<Conference> conferences = this.conferenceService.findAll();
		
		res = new ModelAndView("conference/list");
		res.addObject("conferences", conferences);
		
		return res;
	}
	
	
	
//	//edit actor information
//		@RequestMapping(value = "/edit", method = RequestMethod.GET)
//		public ModelAndView edit() {
//			ModelAndView res;
//			final ActorForm actorForm = this.actorService.formatForm(this.actorService.findPrincipal());
//			res = this.createActorEditModelAndView(actorForm);
//			return res;
//		}
//		//save actor information
//		@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
//		public ModelAndView commit(@ModelAttribute("actorForm") @Valid final ActorForm actorForm, final BindingResult binding) {
//			ModelAndView res;
//			if (binding.hasErrors())
//				res = this.createActorEditModelAndView(actorForm);
//			else
//				try {
//					this.actorService.save(this.actorService.parseForm(actorForm));
//					res = new ModelAndView("redirect:profile.do");
//				} catch (final Exception e) {
//					res = this.createActorEditModelAndView(actorForm, "actor.commit.error");
//				}
//			return res;
//		}
//		
		
		
		protected ModelAndView createEditModelAndView(Conference conference) {
			ModelAndView res;
			res = this.createEditModelAndView(conference, null);
			return res;
		}

		protected ModelAndView createEditModelAndView(Conference conference, String messageCode) {
			ModelAndView res;

			res = new ModelAndView("conference/edit");
			res.addObject("conference", conference);
			res.addObject("message", messageCode);
			return res;
		}
	
	
}
