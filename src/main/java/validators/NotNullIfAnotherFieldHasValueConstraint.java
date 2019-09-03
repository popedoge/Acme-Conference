
package validators;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = EmailValidator.class)
@Target({
	ElementType.TYPE, ElementType.ANNOTATION_TYPE
})
@Retention(RetentionPolicy.RUNTIME)
public @interface NotNullIfAnotherFieldHasValueConstraint {

	String fieldName();
	String fieldValue();
	String dependFieldName();

	String message() default "activity.submission.error";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};


	@Documented
	@Constraint(validatedBy = EmailValidator.class)
	@Target({
		ElementType.METHOD, ElementType.FIELD
	})
	@Retention(RetentionPolicy.RUNTIME)
	@interface List {

		NotNullIfAnotherFieldHasValueConstraint[] value();
	}
}
