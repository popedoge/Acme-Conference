
package domain;

import org.hibernate.validator.constraints.NotBlank;

public class SocialNetwork {

	private String	name;
	private String	icon;


	@NotBlank
	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	@NotBlank
	public String getIcon() {
		return this.icon;
	}

	public void setIcon(final String icon) {
		this.icon = icon;
	}

}
