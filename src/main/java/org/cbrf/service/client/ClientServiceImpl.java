package org.cbrf.service;

import lombok.AllArgsConstructor;
import org.cbrf.dto.ClientInfo;
import org.cbrf.model.Account;
import org.cbrf.model.Client;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@AllArgsConstructor
public class ClientService {

    private final Scanner scanner;

    public void createClient(List<Client> clients) {
        System.out.print("Введите ФИО клиента: ");
            String fullName = scanner.nextLine();
        System.out.print("Введите номер телефона: ");
            String phoneNumber = scanner.nextLine();
        System.out.print("Введите ИНН: ");
            String inn = scanner.nextLine();
        System.out.print("Введите адрес места жительства: ");
            String address = scanner.nextLine();
        toClientInfo(fullName,phoneNumber,inn,address);
            Client client = new Client(toClientInfo(fullName,phoneNumber,inn,address));
        System.out.println("Клиент успешно создан.");
            clients.add(client);
    }

    public Client selectClient(List<Client> clients) {
        System.out.println("Выберите клиента:");
        for (int i = 0; i < clients.size(); i++) {
            System.out.println((i + 1) + ". " + clients.get(i).getClientInfo().getFullName());
        }
        System.out.print("Введите номер клиента: ");
            int clientIndex = scanner.nextInt() - 1;
            scanner.nextLine(); // consume newline

        if (clientIndex >= 0 && clientIndex < clients.size()) {
            return clients.get(clientIndex);
        } else {
            System.out.println("Неверный выбор клиента.");
            return null;
        }
    }

    private ClientInfo toClientInfo(String fullName, String phoneNumber, String inn, String address) {
        List<Account> accounts = new ArrayList<>();
        return ClientInfo
                .builder()
                    .fullName(fullName)
                    .phoneNumber(phoneNumber)
                    .inn(inn)
                    .address(address)
                    .accounts(accounts)
                .build();
    }
}
