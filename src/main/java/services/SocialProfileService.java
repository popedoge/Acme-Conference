
package services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.SocialProfileRepository;
import domain.Actor;
import domain.SocialNetwork;
import domain.SocialProfile;
import forms.SocialProfileForm;

@Service
@Transactional
public class SocialProfileService {

	@Autowired
	private SocialProfileRepository	socialProfRepo;
	@Autowired
	private ActorService			actorService;
	@Autowired
	private SocialNetworkService	socialNetService;


	public SocialProfile create() {
		final SocialProfile res = new SocialProfile();
		res.setOwner(this.actorService.findPrincipal());
		res.setNetwork(new SocialNetwork());
		return res;
	}

	public SocialProfile findById(final int id) {
		return this.socialProfRepo.findOne(id);
	}

	public SocialProfileForm formatForm(final SocialProfile profile) {
		final SocialProfileForm res = new SocialProfileForm();
		res.setId(profile.getId());
		res.setNetwork(profile.getNetwork().getId());
		res.setUrl(profile.getUrl());
		return res;
	}

	public SocialProfile parseForm(final SocialProfileForm form) {
		SocialProfile res;
		if (form.getId() == 0)
			res = new SocialProfile();
		else
			res = this.socialProfRepo.findOne(form.getId());
		res.setOwner(this.actorService.findPrincipal());
		res.setNetwork(this.socialNetService.findById(form.getNetwork()));
		res.setUrl(form.getUrl());
		return res;
	}

	public List<SocialProfile> findByActor(final Actor actor) {
		return this.socialProfRepo.findByActor(actor.getId());
	}

	public SocialProfile save(final SocialProfile profile) {
		return this.socialProfRepo.save(profile);
	}

	public void delete(final int id) {
		this.socialProfRepo.delete(id);
	}
}
