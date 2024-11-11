package org.cbrf.util;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.hibernate.validator.HibernateValidator;
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator;

import java.util.Set;

public class ValidationService {
    private static final ValidatorFactory factory = Validation.byProvider(HibernateValidator.class)
            .configure()
            .messageInterpolator(new ParameterMessageInterpolator())
            .buildValidatorFactory();

    private static final Validator validator = factory.getValidator();

    public static <T> void validate(T object) {
        Set<ConstraintViolation<T>> violations = validator.validate(object);
        if (!violations.isEmpty()) {
            StringBuilder errorMessage = new StringBuilder("Ошибки валидации:");
            for (ConstraintViolation<T> violation : violations) {
                errorMessage.append("\n").append(violation.getMessage());
            }
            throw new IllegalArgumentException(errorMessage.toString());
        }
    }
}