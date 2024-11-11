package org.cbrf.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import org.cbrf.dto.AccountInfo;

@AllArgsConstructor
@Setter
@Getter
public class Account {
    private AccountInfo accountInfo;

    public void deposit(double amount) {
        if (isValidAmount(amount)) {
            updateBalance(amount);
        }
    }

    public void withdraw(double amount) {
        if (isValidAmount(amount) && hasSufficientBalance(amount)) {
            updateBalance(-amount);
        }
    }

    private boolean isValidAmount(double amount) {
        return amount > 0;
    }

    private boolean hasSufficientBalance(double amount) {
        return accountInfo.getBalance() >= amount;
    }

    private void updateBalance(double amount) {
        accountInfo.setBalance(accountInfo.getBalance() + amount);
    }
}
