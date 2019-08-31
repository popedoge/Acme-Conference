
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

	private Actor			owner;
	private String			title;
	private String			acronym;
	private String			venue;
	private String			summary;
	private Integer			fee;

	// DL= DeadLine

	private Date			submissionDL;
	private Date			notificationDL;
	private Date			cameraDL;
	private Date			startDate;
	private Date			endDate;

	private List<Activity>	activities;


	@ManyToMany
	public List<Activity> getActivities() {
		return this.activities;
	}
	public void setActivities(final List<Activity> activities) {
		this.activities = activities;
	}
	@ManyToOne
	public Actor getOwner() {
		return this.owner;
	}
	public void setOwner(final Actor owner) {
		this.owner = owner;
	}
	@NotBlank
	public String getTitle() {
		return this.title;
	}
	public void setTitle(final String title) {
		this.title = title;
	}
	@NotBlank
	public String getAcronym() {
		return this.acronym;
	}
	public void setAcronym(final String acronym) {
		this.acronym = acronym;
	}
	@NotBlank
	public String getVenue() {
		return this.venue;
	}
	public void setVenue(final String venue) {
		this.venue = venue;
	}
	@NotBlank
	public String getSummary() {
		return this.summary;
	}
	public void setSummary(final String summary) {
		this.summary = summary;
	}
	@NotNull
	public Integer getFee() {
		return this.fee;
	}
	public void setFee(final Integer fee) {
		this.fee = fee;
	}

	@Future
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getSubmissionDL() {
		return this.submissionDL;
	}
	public void setSubmissionDL(final Date submissionDL) {
		this.submissionDL = submissionDL;
	}

	@Future
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getNotificationDL() {
		return this.notificationDL;
	}

	public void setNotificationDL(final Date notificationDL) {
		this.notificationDL = notificationDL;
	}

	@Future
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getCameraDL() {
		return this.cameraDL;
	}

	public void setCameraDL(final Date cameraDL) {
		this.cameraDL = cameraDL;
	}

	@Future
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(final Date startDate) {
		this.startDate = startDate;
	}

	@Future
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getEndDate() {
		return this.endDate;
	}
	public void setEndDate(final Date endDate) {
		this.endDate = endDate;
	}

}
