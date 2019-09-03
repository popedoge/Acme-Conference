
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Paper extends DomainEntity {

	private String	title;
	private String	author;
	private String	summary;
	private String	URL;
	private boolean	cameraReady;


	public boolean getCameraReady() {
		return this.cameraReady;
	}

	public void setCameraReady(final boolean cameraReady) {
		this.cameraReady = cameraReady;
	}
	@NotBlank
	public String getTitle() {
		return this.title;
	}
	public void setTitle(final String title) {
		this.title = title;
	}
	@NotBlank
	public String getAuthor() {
		return this.author;
	}
	public void setAuthor(final String author) {
		this.author = author;
	}
	@NotBlank
	public String getSummary() {
		return this.summary;
	}
	public void setSummary(final String summary) {
		this.summary = summary;
	}
	@org.hibernate.validator.constraints.URL
	@NotBlank
	public String getURL() {
		return this.URL;
	}
	public void setURL(final String uRL) {
		this.URL = uRL;
	}

}
