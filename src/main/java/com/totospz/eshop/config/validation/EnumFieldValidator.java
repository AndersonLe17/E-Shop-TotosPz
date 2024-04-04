package com.totospz.eshop.config.validation;

import com.totospz.eshop.config.validation.annotation.EnumField;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EnumFieldValidator implements ConstraintValidator<EnumField, CharSequence> {

    private List<String> acceptedValues;

    @Override
    public void initialize(EnumField constraintAnnotation) {
        acceptedValues = Stream.of(constraintAnnotation.enumClass().getEnumConstants())
                .map(Enum::name)
                .collect(Collectors.toList());
    }

    @Override
    public boolean isValid(CharSequence charSequence, ConstraintValidatorContext constraintValidatorContext) {
        if (charSequence == null)
            return true;
        return acceptedValues.contains(charSequence.toString());
    }
}
