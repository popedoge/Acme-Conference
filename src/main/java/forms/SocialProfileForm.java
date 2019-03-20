
package forms;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.URL;

public class SocialProfileForm {

	private Integer	id;
	private Integer	network;
	private String	url;


	@NotNull
	public Integer getId() {
		return this.id;
	}

	public void setId(final Integer id) {
		this.id = id;
	}

	@NotNull
	public Integer getNetwork() {
		return this.network;
	}

	public void setNetwork(final Integer network) {
		this.network = network;
	}

	@URL
	public String getUrl() {
		return this.url;
	}

	public void setUrl(final String url) {
		this.url = url;
	}

}
