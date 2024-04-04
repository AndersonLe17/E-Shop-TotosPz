package com.totospz.eshop.config.validation;

import com.totospz.eshop.config.validation.annotation.NoBlank;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NoBlankValidator implements ConstraintValidator<NoBlank, CharSequence> {

    @Override
    public void initialize(NoBlank constraintAnnotation) {}

    @Override
    public boolean isValid(CharSequence charSequence, ConstraintValidatorContext constraintValidatorContext) {
        if (charSequence == null) return true;

        String textValue = charSequence.toString();
        if (!textValue.trim().equals(textValue)) return false;
        if (textValue.trim().isEmpty()) return false;
        return true;
    }
}
