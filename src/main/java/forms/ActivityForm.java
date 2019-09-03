
package forms;

import java.util.Date;
import java.util.List;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import domain.Section;
import validators.NotNullIfAnotherFieldHasValueConstraint;

@NotNullIfAnotherFieldHasValueConstraint(fieldName = "type", fieldValue = "2", dependFieldName = "submssionId")
public class ActivityForm {

	private Integer			conferenceId;
	private Integer			id;
	private Integer			type;
	private String			title;
	private String			speakers;
	private String			summary;
	private String			location;
	private Date			startDate;
	private Date			endDate;
	private List<Section>	sections;
	private Integer			submissionId;


	@NotBlank
	public String getLocation() {
		return this.location;
	}

	public void setLocation(final String location) {
		this.location = location;
	}

	@NotNull
	public Integer getConferenceId() {
		return this.conferenceId;
	}

	public void setConferenceId(final Integer conferenceId) {
		this.conferenceId = conferenceId;
	}
	@NotNull
	public Integer getId() {
		return this.id;
	}

	public void setId(final Integer id) {
		this.id = id;
	}
	@NotNull
	public Integer getType() {
		return this.type;
	}

	public void setType(final Integer type) {
		this.type = type;
	}
	@NotBlank
	public String getTitle() {
		return this.title;
	}

	public void setTitle(final String title) {
		this.title = title;
	}
	@NotBlank
	public String getSpeakers() {
		return this.speakers;
	}

	public void setSpeakers(final String speakers) {
		this.speakers = speakers;
	}
	@NotBlank
	public String getSummary() {
		return this.summary;
	}

	public void setSummary(final String summary) {
		this.summary = summary;
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

	public List<Section> getSections() {
		return this.sections;
	}

	public void setSections(final List<Section> sections) {
		this.sections = sections;
	}

	public Integer getSubmissionId() {
		return this.submissionId;
	}

	public void setSubmissionId(final Integer submissionId) {
		this.submissionId = submissionId;
	}
}
