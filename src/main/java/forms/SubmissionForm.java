package forms;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

import domain.Submission;

public class SubmissionForm {
	public int id;
	public int owner;
	public int conferece;
	public String ticker;
	public String status;
	public int paperId;
	public String paperTitle;
	public String paperAuthor;
	public String paperSummary;
	public String paperUrl;
	public int cameraId;
	public String cameraTitle;
	public String cameraAuthor;
	public String cameraSummary;
	public String cameraUrl;

	public int getId() {
		return this.id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@NotBlank
	public int getOwner() {
		return this.owner;
	}
	public void setOwner(int owner) {
		this.owner = owner;
	}
	@NotBlank
	public int getConferece() {
		return this.conferece;
	}
	public void setConferece(int conferece) {
		this.conferece = conferece;
	}
	@NotBlank
	@Pattern(regexp = "^[A-Z]{3,}-\\w{4,}$")
	public String getTicker() {
		return this.ticker;
	}
	public void setTicker(String ticker) {
		this.ticker = ticker;
	}
	@NotBlank
	@Pattern(regexp = "^" + Submission.PENDING + "|" + Submission.ACCEPTED
			+ "|" + Submission.REJECTED + "$")
	public String getStatus() {
		return this.status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getPaperId() {
		return this.paperId;
	}
	public void setPaperId(int paperId) {
		this.paperId = paperId;
	}
	public String getPaperTitle() {
		return this.paperTitle;
	}
	public void setPaperTitle(String paperTitle) {
		this.paperTitle = paperTitle;
	}
	public String getPaperAuthor() {
		return this.paperAuthor;
	}
	public void setPaperAuthor(String paperAuthor) {
		this.paperAuthor = paperAuthor;
	}
	public String getPaperSummary() {
		return this.paperSummary;
	}
	public void setPaperSummary(String paperSummary) {
		this.paperSummary = paperSummary;
	}
	public String getPaperUrl() {
		return this.paperUrl;
	}
	public void setPaperUrl(String paperUrl) {
		this.paperUrl = paperUrl;
	}
	public int getCameraId() {
		return this.cameraId;
	}
	public void setCameraId(int cameraId) {
		this.cameraId = cameraId;
	}
	public String getCameraTitle() {
		return this.cameraTitle;
	}
	public void setCameraTitle(String cameraTitle) {
		this.cameraTitle = cameraTitle;
	}
	public String getCameraAuthor() {
		return this.cameraAuthor;
	}
	public void setCameraAuthor(String cameraAuthor) {
		this.cameraAuthor = cameraAuthor;
	}
	public String getCameraSummary() {
		return this.cameraSummary;
	}
	public void setCameraSummary(String cameraSummary) {
		this.cameraSummary = cameraSummary;
	}
	public String getCameraUrl() {
		return this.cameraUrl;
	}
	public void setCameraUrl(String cameraUrl) {
		this.cameraUrl = cameraUrl;
	}

}
