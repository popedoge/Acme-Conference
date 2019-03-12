
package domain;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

public class SocialProfile extends DomainEntity {

	private Actor			owner;
	private SocialNetwork	network;
	private String			url;


	@NotNull
	public Actor getOwner() {
		return this.owner;
	}

	public void setOwner(final Actor owner) {
		this.owner = owner;
	}

	@NotNull
	public SocialNetwork getNetwork() {
		return this.network;
	}

	public void setNetwork(final SocialNetwork network) {
		this.network = network;
	}

	@URL
	@NotBlank
	public String getUrl() {
		return this.url;
	}

	public void setUrl(final String url) {
		this.url = url;
	}

}
