
package domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Range;

@Entity
@Access(AccessType.PROPERTY)
public class Report extends DomainEntity {

	private Actor				owner;
	private Submission			submission;
	private Integer				originalityScore;
	private Integer				qualityScore;
	private Integer				readabilityScore;
	private String				decision;
	private String				comments;

	public static final String	ACCEPT	= "report.accept";
	public static final String	REJECT	= "report.reject";
	public static final String	BORDER	= "report.border";


	public List<String> returnDecisonList() {
		final List<String> res = new ArrayList<>();
		res.add(Report.ACCEPT);
		res.add(Report.REJECT);
		res.add(Report.BORDER);
		return res;
	}

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
	public Submission getSubmission() {
		return this.submission;
	}

	public void setSubmission(final Submission submission) {
		this.submission = submission;
	}
	@Range(min = 0, max = 10)
	public Integer getOriginalityScore() {
		return this.originalityScore;
	}

	public void setOriginalityScore(final Integer originalityScore) {
		this.originalityScore = originalityScore;
	}
	@Range(min = 0, max = 10)
	public Integer getQualityScore() {
		return this.qualityScore;
	}

	public void setQualityScore(final Integer qualityScore) {
		this.qualityScore = qualityScore;
	}
	@Range(min = 0, max = 10)
	public Integer getReadabilityScore() {
		return this.readabilityScore;
	}

	public void setReadabilityScore(final Integer readabilityScore) {
		this.readabilityScore = readabilityScore;
	}
	@Pattern(regexp = "^" + Report.ACCEPT + "|" + Report.BORDER + "|" + Report.REJECT + "$")
	public String getDecision() {
		return this.decision;
	}

	public void setDecision(final String decision) {
		this.decision = decision;
	}

	public String getComments() {
		return this.comments;
	}

	public void setComments(final String comments) {
		this.comments = comments;
	}

}
