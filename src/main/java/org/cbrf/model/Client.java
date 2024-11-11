package org.cbrf.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.cbrf.dto.ClientInfo;
import org.cbrf.model.enums.AccountStatus;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
public class Client {
    private ClientInfo clientInfo;

    public void addAccount(Account account) {
        clientInfo.getAccounts().add(account);
    }

    public boolean hasOpenAccount(List<Account> accounts) {
        for (Account account : accounts) {
            if (account.getAccountInfo().getStatus() == AccountStatus.OPEN) {
                return true;
            }
        }
        return false;
    }

    public List<Account> getOpenAccounts() {
        List<Account> openAccounts = new ArrayList<>();
        for (Account account : clientInfo.getAccounts()) {
            if (account.getAccountInfo().getStatus() == AccountStatus.OPEN) {
                openAccounts.add(account);
            }
        }
        return openAccounts;
    }
}


