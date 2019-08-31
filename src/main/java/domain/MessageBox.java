
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class MessageBox extends DomainEntity {

	//relation
	private Actor				owner;
	private MessageBox			parent;
	//attributes
	private String				name;
	private String				category;

	public static final String	ROOT		= "ROOT";
	public static final String	NOTIF		= "NOTIF";
	public static final String	INBOX		= "INBOX";
	public static final String	OUTBOX		= "OUTBOX";
	public static final String	TRASHBOX	= "TRASHBOX";
	public static final String	SPAMBOX		= "SPAMBOX";


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
	@Pattern(regexp = "^" + MessageBox.ROOT + "|" + MessageBox.NOTIF + "|" + MessageBox.INBOX + "|" + MessageBox.OUTBOX + "|" + MessageBox.SPAMBOX + "|" + MessageBox.TRASHBOX + "$")
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
