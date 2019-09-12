
package validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.beanutils.BeanUtils;

public class NotNullIfAnotherFieldHasValueValidator implements ConstraintValidator<NotNullIfAnotherFieldHasValueConstraint, Object> {

	private String	fieldName;
	private String	expectedFieldValue;
	private String	dependFieldName;


	@Override
	public void initialize(final NotNullIfAnotherFieldHasValueConstraint annotation) {
		this.fieldName = annotation.fieldName();
		this.expectedFieldValue = annotation.fieldValue();
		this.dependFieldName = annotation.dependFieldName();
	}

	@Override
	public boolean isValid(final Object value, final ConstraintValidatorContext ctx) {

		if (value == null)
			return true;

		try {
			final String fieldValue = BeanUtils.getProperty(value, this.fieldName);
			final String dependFieldValue = BeanUtils.getProperty(value, this.dependFieldName);

			if (this.expectedFieldValue.equals(fieldValue) && dependFieldValue == null) {
				ctx.disableDefaultConstraintViolation();
				ctx.buildConstraintViolationWithTemplate(ctx.getDefaultConstraintMessageTemplate()).addNode(this.dependFieldName).addConstraintViolation();
				return false;
			}

		} catch (final Exception e) {
			throw new RuntimeException(e);
		}

		return true;
	}

}
