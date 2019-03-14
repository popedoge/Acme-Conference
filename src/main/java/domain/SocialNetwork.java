
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class SocialNetwork extends DomainEntity {

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
