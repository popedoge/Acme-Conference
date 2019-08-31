
package domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Message extends DomainEntity {

	// relations
	private Actor				sender;
	private String				senderAlias;
	private List<Actor>			recipients;
	private List<MessageBox>	container;
	// attributes
	private String				subject;
	private Date				deliveryDate;
	private String				body;
	private Topic				topic;
	private boolean				tick;


	public boolean getTick() {
		return this.tick;
	}

	public void setTick(final boolean tick) {
		this.tick = tick;
	}

	public String getSenderAlias() {
		return this.senderAlias;
	}

	public void setSenderAlias(final String senderAlias) {
		this.senderAlias = senderAlias;
	}

	@ManyToOne(optional = true)
	public Actor getSender() {
		return this.sender;
	}

	public void setSender(final Actor sender) {
		this.sender = sender;
	}

	// admin can send message to multiple recipients
	@ManyToMany
	public List<Actor> getRecipients() {
		return this.recipients;
	}

	public void setRecipients(final List<Actor> recipients) {
		this.recipients = recipients;
	}

	@NotNull
	@ManyToMany
	public List<MessageBox> getContainer() {
		return this.container;
	}

	public void setContainer(final List<MessageBox> container) {
		this.container = container;
	}

	public String getSubject() {
		return this.subject;
	}

	public void setSubject(final String subject) {
		this.subject = subject;
	}

	@Past
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getDeliveryDate() {
		return this.deliveryDate;
	}

	public void setDeliveryDate(final Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public String getBody() {
		return this.body;
	}

	public void setBody(final String body) {
		this.body = body;
	}

	@ManyToOne(optional = false)
	public Topic getTopic() {
		return this.topic;
	}

	public void setTopic(final Topic topic) {
		this.topic = topic;
	}

}
