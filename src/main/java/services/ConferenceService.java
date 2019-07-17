package services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.Conference;
import repositories.ConferenceRepository;
import security.UserAccountService;

@Service
@Transactional
public class ConferenceService {
	
		// Managed repository -----------------------------------------------------

		@Autowired
		private ConferenceRepository	conferenceRepository;

		// Supporting services ----------------------------------------------------

		@Autowired
		private UserAccountService		userAccountService;
		@Autowired
		private ActorService			actorService;

		public Conference findById(int id) {
			return this.conferenceRepository.findOne(id);
		}

		public Conference findByPrincipal() {
			//this.actorService.assertPrincipalAuthority("ADMIN");
			int actorID = this.actorService.findPrincipal().getId();
			Conference res = conferenceRepository.findOne(actorID);
			return res;
		}
		
		public Conference initialize(Conference lorem) {
//			lorem.setPublishDate(new Date());
//			lorem.setTicker(this.createTicker());
//			lorem.setLocked(false);
			return lorem;
		}
		
		public Conference save(Conference conference) {
			if (conference.getId() == 0) {
				conference = this.initialize(conference);
			}
			return this.conferenceRepository.save(conference);
		}
		
		public List<Conference> findAll() {
			return this.conferenceRepository.findAll();
		}
		
		public Conference create() {
//			Customer customer = this.cs.findPrincipal();
			Conference res = new Conference();
//			res.setLocked(false);
//			res.setTicker("TEMP");
//			res.setPublishDate(new Date());
//
//			res.setAuthor(customer);
			return res;
		}
		
		public void delete(Conference conference) {
			//Customer customer = this.customerService.findPrincipal();
			//Assert.isTrue(conference.getAuthor().equals(customer));

			this.conferenceRepository.delete(conference);
		}


}
