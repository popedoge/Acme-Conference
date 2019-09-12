
package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.Actor;
import domain.ActorPreferences;
import forms.PreferencesForm;
import repositories.ActorPreferencesRepository;

@Service
@Transactional
public class ActorPreferencesService {

	@Autowired
	private ActorPreferencesRepository	preferencesRepo;
	@Autowired
	private ActorService				actorService;


	public ActorPreferences findById(final int id) {
		return this.preferencesRepo.findOne(id);
	}

	public ActorPreferences findByActor(final Actor actor) {
		return this.preferencesRepo.findByActor(actor.getId());
	}

	public ActorPreferences findByPrincipal() {
		final Actor actor = this.actorService.findPrincipal();
		return this.preferencesRepo.findByActor(actor.getId());
	}

	public ActorPreferences save(final ActorPreferences preferences) {
		return this.preferencesRepo.save(preferences);
	}

	public ActorPreferences save(final PreferencesForm form) {
		return this.save(this.parseForm(form));
	}

	public ActorPreferences parseForm(final PreferencesForm form) {
		final ActorPreferences res = this.findByPrincipal();

		res.setDisplayEmail(form.getDisplayEmail());

		res.setMessageSignature(form.getMessageSignature());
		return res;
	}
	public PreferencesForm formatForm(final ActorPreferences preferences) {
		final PreferencesForm res = new PreferencesForm();

		res.setDisplayEmail(preferences.getDisplayEmail());

		res.setMessageSignature(preferences.getMessageSignature());
		return res;
	}

	public ActorPreferences create(final Actor actor) {
		final ActorPreferences preferences = new ActorPreferences();
		preferences.setOwner(actor);

		preferences.setDisplayEmail(true);
		preferences.setMessageSignature("- " + actor.getUser().getUsername());
		return preferences;
	}
}
