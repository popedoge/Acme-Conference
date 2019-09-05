
package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Lorem extends DomainEntity {

	//TODO: change reference
	private Conference	reference;
	//TODO: add necessary attributes
	private String		ticker;
	private Date		publicationMoment;
	private String		body;
	private Boolean		locked;


	@NotNull
	@ManyToOne
	public Conference getReference() {
		return this.reference;
	}

	public void setReference(final Conference reference) {
		this.reference = reference;
	}

	@NotBlank
	@Pattern(regexp = "^\\w{6,}$")
	public String getTicker() {
		return this.ticker;
	}

	public void setTicker(final String ticker) {
		this.ticker = ticker;
	}

	@Past
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getPublicationMoment() {
		return this.publicationMoment;
	}

	public void setPublicationMoment(final Date publicationMoment) {
		this.publicationMoment = publicationMoment;
	}

	public String getBody() {
		return this.body;
	}

	public void setBody(final String body) {
		this.body = body;
	}

	@NotNull
	public Boolean getLocked() {
		return this.locked;
	}

	public void setLocked(final Boolean locked) {
		this.locked = locked;
	}

}
