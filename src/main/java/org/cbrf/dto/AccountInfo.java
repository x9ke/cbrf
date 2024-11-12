package org.cbrf.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import lombok.Builder;
import lombok.Data;

import org.cbrf.model.enums.AccountStatus;
import org.cbrf.validator.ValidCurrency;
import org.cbrf.validator.ValidationService;

import java.math.BigDecimal;

/**
 * Класс, представляющий информацию о банковском счете.
 * <p>
 * Этот класс хранит данные о банковском счете, включая номер счета, баланс, статус,
 * BIK и валюту счета.
 * </p>
 *
 * @see AccountStatus
 * @see ValidationService
 */
@Data
@Builder
public class AccountInfo {

    /**
     * Номер счета.
     * <p>
     * Номер счета должен состоять из 20 цифр.
     * </p>
     */
  //  @NotEmpty(message = "Номер счета не может быть пустым")
  //  @Pattern(regexp = "\\d{20}", message = "Номер счета должен состоять из 20 цифр")
    private String accountNumber;

    /**
     * Баланс счета.
     * <p>
     * Баланс должен быть положительным числом или равным нулю.
     * </p>
     */
   // @DecimalMin(value = "0.0", message = "Баланс не может быть отрицательным")
    private BigDecimal balance;

    /**
     * Статус счета.
     * <p>
     * Статус счета не может быть null.
     * </p>
     */
  //  @NotNull(message = "Статус счета не может быть null")
    private AccountStatus status;

    /**
     * BIK (Банковский идентификационный код).
     * <p>
     * BIK должен состоять из 9 цифр.
     * </p>
     */
  //  @NotEmpty(message = "BIK не может быть пустым")
   // @Pattern(regexp = "\\d{9}", message = "BIK должен состоять из 9 цифр")
    private String bik;

    /**
     * Валюта счета.
     * <p>
     * Значение валюты должно быть валидным.
     * </p>
     */
  //  @ValidCurrency
    private String currency;

    /**
     * Конструктор для создания объекта {@link AccountInfo}.
     *
     * @param accountNumber Номер счета.
     * @param balance Баланс счета.
     * @param status Статус счета.
     * @param bik Банковский идентификационный код.
     * @param currency Валюта счета.
     */
    public AccountInfo(String accountNumber, BigDecimal balance, AccountStatus status, String bik, String currency) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.status = status;
        this.bik = bik;
        this.currency = currency;
        // Валидация объекта
        ValidationService.validate(this);
    }
}