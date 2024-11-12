package org.cbrf.service.operation;

import lombok.AllArgsConstructor;
import org.cbrf.model.Account;
import org.cbrf.model.Client;
import org.cbrf.service.account.AccountService;
import org.cbrf.service.client.ClientService;

import java.util.List;
import java.util.Scanner;

@AllArgsConstructor
public class OperationServiceImpl implements OperationService {

    private final AccountService accountServiceImpl;

    private final ClientService clientServiceImpl;

    private final Scanner scanner;

    public void transfer(List<Client> clients) {
        Client senderClient = clientServiceImpl.selectClient(clients);
        if (senderClient != null) {
            Account senderAccount = accountServiceImpl.selectOpenAccount(senderClient);
            if (senderAccount != null) {
                System.out.print("Введите сумму перевода: ");
                    double amount = scanner.nextDouble();
                    scanner.nextLine(); // consume newline

                Client receiverClient = clientServiceImpl.selectClient(clients);
                if (receiverClient != null) {
                    Account receiverAccount = accountServiceImpl.selectOpenAccount(receiverClient);
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

    public void deposit(List<Client> clients) {
        Client client = clientServiceImpl.selectClient(clients);
        if (client != null) {
            Account account = accountServiceImpl.selectOpenAccount(client);
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
