
package forms;

public class PreferencesForm {

	private boolean	displayName;
	private boolean	displayEmail;
	private boolean	displayAddress;
	private boolean	displayNumber;
	private String	messageSignature;


	public boolean isDisplayName() {
		return this.displayName;
	}

	public void setDisplayName(final boolean displayName) {
		this.displayName = displayName;
	}

	public boolean isDisplayEmail() {
		return this.displayEmail;
	}

	public void setDisplayEmail(final boolean displayEmail) {
		this.displayEmail = displayEmail;
	}

	public boolean isDisplayAddress() {
		return this.displayAddress;
	}

	public void setDisplayAddress(final boolean displayAddress) {
		this.displayAddress = displayAddress;
	}

	public boolean isDisplayNumber() {
		return this.displayNumber;
	}

	public void setDisplayNumber(final boolean displayNumber) {
		this.displayNumber = displayNumber;
	}

	public String getMessageSignature() {
		return this.messageSignature;
	}

	public void setMessageSignature(final String messageSignature) {
		this.messageSignature = messageSignature;
	}

}
