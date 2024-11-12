package org.cbrf.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;

import org.cbrf.model.Account;
import org.cbrf.validator.ValidationService;

import java.util.List;

/**
 * Класс, представляющий информацию о клиенте.
 * <p>
 * Этот класс содержит данные о клиенте, включая его полное имя, номер телефона, ИНН, адрес,
 * а также список его банковских счетов.
 * </p>
 *
 * @see Account
 */
@Builder
@Data
public class ClientInfo {

    /**
     * Полное имя клиента.
     * <p>
     * Имя должно содержать только буквы и пробелы.
     * </p>
     */
   // @NotNull(message = "Полное имя не может быть пустым.")
   // @Pattern(regexp = "^[a-zA-Zа-яА-ЯёЁ\\s]+$", message = "Полное имя может содержать только буквы и пробелы.")
    private String fullName;

    /**
     * Номер телефона клиента.
     * <p>
     * Номер телефона должен быть в формате +код_страны(опционально) и 10 цифр.
     * </p>
     */
   // @NotNull(message = "Номер телефона не может быть пустым.")
   // @Pattern(regexp = "^(\\+\\d{1,3})?\\d{10}$", message = "Неверный формат телефонного номера.")
    private String phoneNumber;

    /**
     * ИНН клиента.
     * <p>
     * ИНН должен состоять из 10 или 12 цифр.
     * </p>
     */
  //  @NotNull(message = "ИНН не может быть пустым.")
  //  @Pattern(regexp = "\\d{10}|\\d{12}", message = "ИНН должен состоять из 10 или 12 цифр.")
    private String inn;

    /**
     * Адрес клиента.
     * <p>
     * Адрес не может быть пустым.
     * </p>
     */
   // @NotBlank(message = "Адрес не может быть пустым.")
    private String address;

    /**
     * Список счетов клиента.
     * <p>
     * Может быть пустым, если у клиента нет счетов.
     * </p>
     */
    private List<Account> accounts;

    /**
     * Конструктор для создания объекта {@link ClientInfo}.
     *
     * @param fullName Полное имя клиента.
     * @param phoneNumber Номер телефона клиента.
     * @param inn ИНН клиента.
     * @param address Адрес клиента.
     * @param accounts Список счетов клиента.
     */
    public ClientInfo(String fullName, String phoneNumber, String inn, String address, List<Account> accounts) {
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.inn = inn;
        this.address = address;
        this.accounts = accounts;

        ValidationService.validate(this);
    }
}
