package com.cross.chain.payment.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class EnumValuePatternValidator implements ConstraintValidator<EnumValuePattern, Enum<?>> {

    private Pattern pattern;

    @Override
    public void initialize(EnumValuePattern constraintAnnotation) {
        try {
            pattern = Pattern.compile(constraintAnnotation.regexp());
        } catch (PatternSyntaxException e){
            throw new IllegalArgumentException("Given regex is invalid", e);
        }
    }

    @Override
    public boolean isValid(Enum<?> value, ConstraintValidatorContext constraintValidatorContext) {
        if(constraintValidatorContext == null){
            return true;
        }
        if(value == null){
            return false;
        }
        Matcher m = pattern.matcher(value.name());
        return m.matches();
    }
}
