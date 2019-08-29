package services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ConferenceRepository;
import security.Authority;
import domain.Actor;
import domain.Conference;

@Service
@Transactional
public class ConferenceService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private ConferenceRepository conferenceRepository;

	// Supporting services ----------------------------------------------------

	@Autowired
	private ActorService actorService;

	public Conference findById(int id) {
		return this.conferenceRepository.findOne(id);
	}

	public Conference findByPrincipal() {
		int actorID = this.actorService.findPrincipal().getId();
		Conference res = this.conferenceRepository.findOne(actorID);
		return res;
	}

	public Conference save(Conference conference) {
		return this.conferenceRepository.save(conference);
	}

	public List<Conference> findAll() {
		return this.conferenceRepository.findAll();
	}

	public Conference create() {
		Actor actor = this.actorService.findPrincipal();
		Assert.isTrue(actor.getUser().checkAuthority(Authority.ADMIN));
		Conference res = new Conference();
		res.setOwner(actor);
		return res;
	}

	public void delete(int id) {
		this.conferenceRepository.delete(id);
	}

}
