package com.cross.chain.payment.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = EnumValuePatternValidator.class)
public @interface EnumValuePattern {
    String regexp();
    String message() default "Invalid data provided.";
    Class<?>[] groups() default{};
    Class<? extends Payload>[] payload() default {};
}