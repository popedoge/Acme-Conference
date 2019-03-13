
package forms;

public class MessageForm {

	public String	recipients;
	public String	subject;
	public String	body;
	public String	priority;
	public Boolean	lock;


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

	public String getPriority() {
		return this.priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

}
