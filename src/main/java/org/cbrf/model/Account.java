package org.cbrf.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import org.cbrf.dto.AccountInfo;

import java.math.BigDecimal;

@AllArgsConstructor
@Setter
@Getter
public class Account {
    private AccountInfo accountInfo;

    public void deposit(BigDecimal amount) {
        if (isValidAmount(amount)) {
            updateBalance(amount);
        }
    }

    public void withdraw(BigDecimal amount) {
        if (isValidAmount(amount) && hasSufficientBalance(amount)) {
            updateBalance(amount.negate());
        }
    }

    private boolean isValidAmount(BigDecimal amount) {
        return amount.compareTo(BigDecimal.ZERO) > 0;
    }

    private boolean hasSufficientBalance(BigDecimal amount) {
        return accountInfo.getBalance().compareTo(amount) >= 0;
    }

    private void updateBalance(BigDecimal amount) {
        accountInfo.setBalance(accountInfo.getBalance().add(amount));
    }
}
