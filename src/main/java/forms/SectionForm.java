
package forms;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import domain.Section;

public class SectionForm {

	private Integer	tutorialId;
	private Section	section;


	@NotNull
	public Integer getTutorialId() {
		return this.tutorialId;
	}

	public void setTutorialId(final Integer tutorialId) {
		this.tutorialId = tutorialId;
	}

	@Valid
	@NotNull
	public Section getSection() {
		return this.section;
	}

	public void setSection(final Section section) {
		this.section = section;
	}

}
