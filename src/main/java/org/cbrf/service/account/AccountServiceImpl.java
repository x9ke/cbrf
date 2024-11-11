package org.cbrf.service;

import lombok.AllArgsConstructor;
import org.cbrf.dto.AccountInfo;
import org.cbrf.model.Account;
import org.cbrf.model.Client;
import org.cbrf.model.enums.AccountStatus;

import java.util.List;
import java.util.Scanner;

@AllArgsConstructor
public class AccountService {

    private final ClientService clientService;

    private final Scanner scanner;

    public void createAccountForClient(List<Client> clients) {
        if (clients.isEmpty()) {
            System.out.println("Клиентов не найдено.");
            return;
        }

        Client client = clientService.selectClient(clients);
        if (client != null) {
            System.out.print("Введите номер счета: ");
                String accountNumber = scanner.nextLine();
            System.out.print("Введите БИК: ");
                String bik = scanner.nextLine();
            System.out.print("Выберите валюту (RUB, EUR, USD): ");
                String currencyInput = scanner.nextLine();
            Account account = new Account(toAccountInfo(accountNumber,bik, currencyInput));
            client.addAccount(account);
            System.out.println("Счет успешно создан.");
        }
    }

    public Account selectOpenAccount(Client client) {
        List<Account> openAccounts = client.getOpenAccounts();
        if (openAccounts.isEmpty()) {
            System.out.println("Открытых счетов не найдено.");
            return null;
        }

        System.out.println("Выберите счет:");
        for (int i = 0; i < openAccounts.size(); i++) {
            System.out.println((i + 1) + ". " + openAccounts.get(i).getAccountInfo().getAccountNumber());
        }
        System.out.print("Введите номер счета: ");
        int accountIndex = scanner.nextInt() - 1;
        scanner.nextLine(); // consume newline

        if (accountIndex >= 0 && accountIndex < openAccounts.size()) {
            return openAccounts.get(accountIndex);
        } else {
            System.out.println("Неверный выбор счета.");
            return null;
        }
    }

    public void closeAccountForClient(List<Client> clients) {
        List<Client> clientsWithOpenAccounts = clients.stream()
                .filter(client -> client.hasOpenAccount(client.getClientInfo().getAccounts()))
                .toList();

        if (clientsWithOpenAccounts.isEmpty()) {
            System.out.println("Клиентов с открытыми счетами не найдено.");
            return;
        }

        Client client = selectClientWithOpenAccounts(clientsWithOpenAccounts);
        if (client != null) {
            Account account = selectOpenAccount(client);
            if (account != null) {
                account.getAccountInfo().setStatus(AccountStatus.CLOSED);
                System.out.println("Счет успешно закрыт.");
            }
        }
    }

    private Client selectClientWithOpenAccounts(List<Client> clientsWithOpenAccounts) {
        System.out.println("Выберите клиента с открытыми счетами:");
        for (int i = 0; i < clientsWithOpenAccounts.size(); i++) {
            System.out.println((i + 1) + ". " + clientsWithOpenAccounts.get(i).getClientInfo().getFullName());
        }
        System.out.print("Введите номер клиента: ");
        int clientIndex = scanner.nextInt() - 1;
        scanner.nextLine();

        if (clientIndex >= 0 && clientIndex < clientsWithOpenAccounts.size()) {
            return clientsWithOpenAccounts.get(clientIndex);
        } else {
            System.out.println("Неверный выбор клиента.");
            return null;
        }
    }

    private AccountInfo toAccountInfo(String accountNumber, String bik, String currency) {
        return AccountInfo
                .builder()
                    .accountNumber(accountNumber)
                    .bik(bik)
                    .balance(0.0)
                    .status(AccountStatus.OPEN)
                    .currency(currency)
                .build();
    }
}
