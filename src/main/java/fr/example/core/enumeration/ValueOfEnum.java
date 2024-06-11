package fr.example.core.enumeration;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.springframework.modulith.NamedInterface;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = ValueOfEnumValidator.class)
@NamedInterface("core-value-of-enum-spi")
public @interface ValueOfEnum {
    Class<? extends Enum<?>> enumClass();

    String regexp();

    String field();

    String message() default "Le champ {field} doit être égal à une des valeurs : {regexp}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
