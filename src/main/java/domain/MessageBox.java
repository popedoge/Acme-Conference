
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class MessageBox extends DomainEntity {

	//relation
	public Actor		owner;
	public MessageBox	parent;
	//attributes
	public String		name;
	public String		category;


	@ManyToOne(optional = false)
	public Actor getOwner() {
		return this.owner;
	}

	public void setOwner(final Actor owner) {
		this.owner = owner;
	}

	//if null : root folder
	@ManyToOne(optional = true)
	public MessageBox getParent() {
		return this.parent;
	}

	public void setParent(final MessageBox parent) {
		this.parent = parent;
	}

	@NotBlank
	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	@NotBlank
	public String getCategory() {
		return this.category;
	}

	public void setCategory(final String category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return String.valueOf(this.getId());
	}
}
