package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Conference extends DomainEntity{
	
	public String title;
	public String acronym;
	public String venue;
	public String summary;
	public Integer fee;
	
	//DL= DeadLine
	
	public Date submissionDL;
	public Date notificationDL;
	public Date cameraDL;
	
	public Date startDate;
	public Date endDate;
	
	@NotBlank
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@NotBlank
	public String getAcronym() {
		return acronym;
	}
	public void setAcronym(String acronym) {
		this.acronym = acronym;
	}
	@NotBlank
	public String getVenue() {
		return venue;
	}
	public void setVenue(String venue) {
		this.venue = venue;
	}
	@NotBlank
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	@NotNull
	public Integer getFee() {
		return fee;
	}
	public void setFee(Integer fee) {
		this.fee = fee;
	}
	
	public Date getSubmissionDL() {
		return submissionDL;
	}
	public void setSubmissionDL(Date submissionDL) {
		this.submissionDL = submissionDL;
	}
	public Date getNotificationDL() {
		return notificationDL;
	}
	public void setNotificationDL(Date notificationDL) {
		this.notificationDL = notificationDL;
	}
	public Date getCameraDL() {
		return cameraDL;
	}
	public void setCameraDL(Date cameraDL) {
		this.cameraDL = cameraDL;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	
}
