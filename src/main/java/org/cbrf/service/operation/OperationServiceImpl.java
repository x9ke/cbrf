package org.cbrf.service;

import lombok.AllArgsConstructor;
import org.cbrf.model.Account;
import org.cbrf.model.Client;

import java.util.List;
import java.util.Scanner;

@AllArgsConstructor
public class OperationService {

    private final AccountService accountService;

    private final ClientService clientService;

    private final Scanner scanner;

    public void transferFunds(List<Client> clients) {
        Client senderClient = clientService.selectClient(clients);
        if (senderClient != null) {
            Account senderAccount = accountService.selectOpenAccount(senderClient);
            if (senderAccount != null) {
                System.out.print("Введите сумму перевода: ");
                    double amount = scanner.nextDouble();
                    scanner.nextLine(); // consume newline

                Client receiverClient = clientService.selectClient(clients);
                if (receiverClient != null) {
                    Account receiverAccount = accountService.selectOpenAccount(receiverClient);
                    if (receiverAccount != null) {
                        if (senderAccount.getAccountInfo().getBalance() >= amount) {
                            senderAccount.withdraw(amount);
                            receiverAccount.deposit(amount);
                            System.out.println("Перевод выполнен успешно.");
                        } else {
                            System.out.println("Недостаточно средств для перевода.");
                        }
                    }
                }
            }
        }
    }

    public void depositFunds(List<Client> clients) {
        Client client = clientService.selectClient(clients);
        if (client != null) {
            Account account = accountService.selectOpenAccount(client);
            if (account != null) {
                System.out.print("Введите сумму для зачисления: ");
                    double amount = scanner.nextDouble();
                    scanner.nextLine();
                    account.deposit(amount);
                System.out.println("Средства успешно зачислены.");
            }
        }
    }
}
