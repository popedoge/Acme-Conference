
package services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.Activity;
import forms.ActivityForm;
import repositories.ActivityRepository;

@Service
@Transactional
public class ActivityService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private ActivityRepository	activityRepo;
	@Autowired
	private PanelService		panelService;
	@Autowired
	private TutorialService		tutorialService;
	@Autowired
	private PresentationService	presentationService;


	// Supporting services ----------------------------------------------------

	public void deleteAll(final List<Activity> activities) {
		this.activityRepo.delete(activities);
	}

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

	public List<Activity> findByConference(final int id) {
		return this.activityRepo.findByConference(id);
	}

	public ActivityForm formatForm(final Activity activity, final Integer conferenceId, Integer type) {
		ActivityForm res = new ActivityForm();
		res.setConferenceId(conferenceId);
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
			res = this.tutorialService.formatForm(res);
			break;
		case 1:
			res = this.presentationService.formatForm(res);
			break;
		}
		return res;
	}

	public Activity create() {
		return new Activity();
	}

	public Activity save(final Activity Activity) {
		return this.activityRepo.save(Activity);
	}

	public Activity save(final ActivityForm form) {
		Activity res = null;
		switch (form.getType()) {
		case 0:
			res = this.panelService.save(form);
			break;
		case 1:
			res = this.presentationService.save(form);
			break;
		case 2:
			res = this.tutorialService.save(form);
			break;
		}

		return res;
	}

	public void delete(final int id) {
		this.activityRepo.delete(id);
	}

	public Activity findById(final int id) {
		return this.activityRepo.findOne(id);
	}

}
