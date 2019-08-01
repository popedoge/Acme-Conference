package domain;

import java.util.Date;

import javax.validation.Valid;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

public class ReviewerRequest {
	public String status;
	public Actor requestee;
	public Date moment;

	public static final String ACCEPTED = "ACCEPTED";
	public static final String DENIED = "DENIED";
	public static final String PENDING = "PENDING...";

	@NotBlank
	@Pattern(regexp = "^" + ReviewerRequest.ACCEPTED + "|"
			+ ReviewerRequest.DENIED + "|" + ReviewerRequest.PENDING + "$")
	public String getStatus() {
		return this.status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Valid
	public Actor getRequestee() {
		return this.requestee;
	}
	public void setRequestee(Actor requestee) {
		this.requestee = requestee;
	}
	@Past
	public Date getMoment() {
		return this.moment;
	}
	public void setMoment(Date moment) {
		this.moment = moment;
	}
}
