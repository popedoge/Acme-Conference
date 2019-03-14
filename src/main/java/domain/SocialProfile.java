
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

@Entity
@Access(AccessType.PROPERTY)
public class SocialProfile extends DomainEntity {

	private Actor			owner;
	private SocialNetwork	network;
	private String			url;


	@ManyToOne(optional = false)
	public Actor getOwner() {
		return this.owner;
	}

	public void setOwner(final Actor owner) {
		this.owner = owner;
	}

	@ManyToOne(optional = false)
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
