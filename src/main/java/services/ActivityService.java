
package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.Activity;
import domain.Conference;
import forms.ActivityForm;
import repositories.ActivityRepository;

@Service
@Transactional
public class ActivityService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private ActivityRepository	ActivityRepo;
	@Autowired
	private PanelService		panelService;
	@Autowired
	private TutorialService		tutorialService;
	@Autowired
	private PresentationService	presentationService;


	// Supporting services ----------------------------------------------------

	public Integer findType(final Activity activity) {
		Integer res = null;
		if (this.panelService.findById(activity.getId()) != null)
			res = 0;
		else if (this.presentationService.findById(activity.getId()) != null)
			res = 1;
		else if (this.tutorialService.findById(activity.getId()) != null)
			res = 2;
		return res;
	}

	public ActivityForm formatForm(final Activity activity, final Conference conference, Integer type) {
		final ActivityForm res = new ActivityForm();
		res.setConferenceId(conference.getId());
		res.setTitle(activity.getTitle());
		res.setId(activity.getId());
		res.setSpeakers(activity.getSpeakers());
		res.setSummary(activity.getSummary());
		res.setStartDate(activity.getStartDate());
		res.setEndDate(activity.getEndDate());
		res.setLocation(activity.getLocation());

		if (type == null || type > 2) {
			type = this.findType(activity);
			res.setType(type);
		} else
			res.setType(type);

		switch (type) {
		case 2:
			return this.tutorialService.formatForm(res);
		case 1:
			return this.presentationService.formatForm(res);
		default:
			return res;
		}

	}

	public Activity parseForm(final ActivityForm form) {
		final Activity res = this.findById(form.getId());
		return res;
	}

	public Activity create() {
		return new Activity();
	}

	public Activity save(final Activity Activity) {
		return this.ActivityRepo.save(Activity);
	}

	public void delete(final int id) {
		this.ActivityRepo.delete(id);
	}

	public Activity findById(final int id) {
		return this.ActivityRepo.findOne(id);
	}

}
