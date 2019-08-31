
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Entity
@Access(AccessType.PROPERTY)
public class Registration extends DomainEntity {

	private Actor		owner;
	private Conference	conference;
	private CreditCard	creditCard;


	@Valid
	@NotNull
	public CreditCard getCreditCard() {
		return this.creditCard;
	}

	public void setCreditCard(final CreditCard creditCard) {
		this.creditCard = creditCard;
	}
	@NotNull
	@ManyToOne
	public Conference getConference() {
		return this.conference;
	}
	public void setConference(final Conference conference) {
		this.conference = conference;
	}
	@NotNull
	@ManyToOne
	public Actor getOwner() {
		return this.owner;
	}
	public void setOwner(final Actor owner) {
		this.owner = owner;
	}

}
