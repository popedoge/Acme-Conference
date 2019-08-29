package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
@Access(AccessType.PROPERTY)
public class Registration extends DomainEntity {
	public Actor owner;
	public Conference conference;

	@NotNull
	@ManyToOne
	public Conference getConference() {
		return this.conference;
	}
	public void setConference(Conference conference) {
		this.conference = conference;
	}
	@NotNull
	@ManyToOne
	public Actor getOwner() {
		return this.owner;
	}
	public void setOwner(Actor owner) {
		this.owner = owner;
	}

}
