package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Paper extends DomainEntity {

	public String title;
	public String author;
	public String summary;
	public String URL;

	@NotBlank
	public String getTitle() {
		return this.title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@NotBlank
	public String getAuthor() {
		return this.author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	@NotBlank
	public String getSummary() {
		return this.summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	@org.hibernate.validator.constraints.URL
	@NotBlank
	public String getURL() {
		return this.URL;
	}
	public void setURL(String uRL) {
		this.URL = uRL;
	}

}
