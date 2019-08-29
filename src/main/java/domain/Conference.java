package domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Conference extends DomainEntity {

	public Actor owner;
	public String title;
	public String acronym;
	public String venue;
	public String summary;
	public Integer fee;

	// DL= DeadLine

	public Date submissionDL;
	public Date notificationDL;
	public Date cameraDL;
	public Date startDate;
	public Date endDate;

	public List<Activity> activities;

	@ManyToMany
	public List<Activity> getActivities() {
		return this.activities;
	}
	public void setActivities(List<Activity> activities) {
		this.activities = activities;
	}
	@ManyToOne
	public Actor getOwner() {
		return this.owner;
	}
	public void setOwner(Actor owner) {
		this.owner = owner;
	}
	@NotBlank
	public String getTitle() {
		return this.title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@NotBlank
	public String getAcronym() {
		return this.acronym;
	}
	public void setAcronym(String acronym) {
		this.acronym = acronym;
	}
	@NotBlank
	public String getVenue() {
		return this.venue;
	}
	public void setVenue(String venue) {
		this.venue = venue;
	}
	@NotBlank
	public String getSummary() {
		return this.summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	@NotNull
	public Integer getFee() {
		return this.fee;
	}
	public void setFee(Integer fee) {
		this.fee = fee;
	}

	@Future
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getSubmissionDL() {
		return this.submissionDL;
	}
	public void setSubmissionDL(Date submissionDL) {
		this.submissionDL = submissionDL;
	}

	@Future
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getNotificationDL() {
		return this.notificationDL;
	}

	public void setNotificationDL(Date notificationDL) {
		this.notificationDL = notificationDL;
	}

	@Future
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getCameraDL() {
		return this.cameraDL;
	}

	public void setCameraDL(Date cameraDL) {
		this.cameraDL = cameraDL;
	}

	@Future
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@Future
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getEndDate() {
		return this.endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

}
