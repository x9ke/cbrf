package org.cbrf.util;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.cbrf.model.enums.Currency;

public class CurrencyValidator implements ConstraintValidator<ValidCurrency, String> {

    @Override
    public void initialize(ValidCurrency constraintAnnotation) {
        // Инициализация, если нужно
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return false; // Если значение null, то невалидно
        }

        try {
            // Преобразуем строку в валюту через valueOf, проверяем, что это корректное значение
            Currency.valueOf(value); // Если значение не соответствует, будет выброшено исключение
            return true;
        } catch (IllegalArgumentException e) {
            // Если значение не найдено в перечислении, возвращаем false
            return false;
        }
    }
}
