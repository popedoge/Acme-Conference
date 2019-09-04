
package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.Panel;
import forms.ActivityForm;
import repositories.PanelRepository;

@Service
@Transactional
public class PanelService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private PanelRepository		panelRepo;
	@Autowired
	private ConferenceService	conferenceService;


	// Supporting services ----------------------------------------------------

	public Panel create() {
		return new Panel();
	}

	public Panel parseForm(final ActivityForm form) {
		final Panel res = this.create();
		res.setId(form.getId());
		res.setConference(this.conferenceService.findById(form.getConferenceId()));
		res.setEndDate(form.getEndDate());
		res.setSpeakers(form.getSpeakers());
		res.setStartDate(form.getStartDate());
		res.setSummary(form.getSummary());
		res.setTitle(form.getTitle());
		res.setLocation(form.getLocation());

		return res;
	}

	public Panel save(final ActivityForm form) {
		final Panel p = this.parseForm(form);
		return this.save(p);
	}

	public Panel save(final Panel Panel) {
		return this.panelRepo.save(Panel);
	}

	public void delete(final int id) {
		this.panelRepo.delete(id);
	}

	public Panel findById(final int id) {
		return this.panelRepo.findOne(id);
	}

}
