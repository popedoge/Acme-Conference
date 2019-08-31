
package domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Activity extends DomainEntity {

	private String	title;
	private String	speakers;
	private String	location;
	private String	summary;
	private Date	startDate;


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
	public String getLocation() {
		return this.location;
	}
	public void setLocation(final String location) {
		this.location = location;
	}
	@NotBlank
	public String getSummary() {
		return this.summary;
	}
	public void setSummary(final String summary) {
		this.summary = summary;
	}
	@NotNull
	@Future
	public Date getStartDate() {
		return this.startDate;
	}
	public void setStartDate(final Date startDate) {
		this.startDate = startDate;
	}

}
