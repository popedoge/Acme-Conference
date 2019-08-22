package forms;

public class MessageForm {

	public String recipients;
	public String subject;
	public String body;
	public Integer topic;
	public Boolean lock;

	public Boolean getLock() {
		return this.lock;
	}

	public void setLock(Boolean lock) {
		this.lock = lock;
	}

	public String getRecipients() {
		return this.recipients;
	}

	public void setRecipients(String recipients) {
		this.recipients = recipients;
	}

	public String getSubject() {
		return this.subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return this.body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Integer getTopic() {
		return this.topic;
	}

	public void setTopic(Integer topic) {
		this.topic = topic;
	}

}
