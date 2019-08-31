
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Submission extends DomainEntity {

	private Actor				owner;
	private Conference			conference;
	private String				ticker;
	private String				status;
	private Paper				paper;
	private Paper				cameraPaper;

	public static final String	PENDING		= "submission.pending";
	public static final String	ACCEPTED	= "submission.accepted";
	public static final String	REJECTED	= "submission.rejected";


	@NotNull
	@ManyToOne
	public Actor getOwner() {
		return this.owner;
	}

	public void setOwner(final Actor owner) {
		this.owner = owner;
	}
	@NotNull
	@ManyToOne
	public Conference getConference() {
		return this.conference;
	}

	public void setConference(final Conference conference) {
		this.conference = conference;
	}
	@NotBlank
	@Pattern(regexp = "^[A-Z]{3,}-\\w{4,}$")
	public String getTicker() {
		return this.ticker;
	}
	public void setTicker(final String ticker) {
		this.ticker = ticker;
	}
	@NotBlank
	@Pattern(regexp = "^" + Submission.PENDING + "|" + Submission.ACCEPTED + "|" + Submission.REJECTED + "$")
	public String getStatus() {
		return this.status;
	}
	public void setStatus(final String status) {
		this.status = status;
	}

	@OneToOne(optional = true, cascade = CascadeType.ALL)
	public Paper getPaper() {
		return this.paper;
	}
	public void setPaper(final Paper paper) {
		this.paper = paper;
	}

	@OneToOne(optional = true, cascade = CascadeType.ALL)
	public Paper getCameraPaper() {
		return this.cameraPaper;
	}
	public void setCameraPaper(final Paper cameraPaper) {
		this.cameraPaper = cameraPaper;
	}

}
