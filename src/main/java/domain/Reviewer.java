package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;

@Entity
@Access(AccessType.PROPERTY)
public class Reviewer extends Actor {

	public String expertise;

	public String getExpertise() {
		return this.expertise;
	}

	public void setExpertise(String expertise) {
		this.expertise = expertise;
	}
}
