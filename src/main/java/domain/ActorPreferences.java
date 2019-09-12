
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Entity
@Access(AccessType.PROPERTY)
public class ActorPreferences extends DomainEntity {

	private Actor	owner;
	private boolean	displayEmail;
	private String	messageSignature;


	@OneToOne
	@NotNull
	public Actor getOwner() {
		return this.owner;
	}

	public void setOwner(final Actor owner) {
		this.owner = owner;
	}

	public boolean getDisplayEmail() {
		return this.displayEmail;
	}

	public void setDisplayEmail(final boolean displayEmail) {
		this.displayEmail = displayEmail;
	}

	public String getMessageSignature() {
		return this.messageSignature;
	}

	public void setMessageSignature(final String messageSignature) {
		this.messageSignature = messageSignature;
	}

}
