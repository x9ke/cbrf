package org.cbrf;

import lombok.extern.slf4j.Slf4j;
import org.cbrf.model.Client;
import org.cbrf.service.AccountService;
import org.cbrf.service.ClientService;
import org.cbrf.service.OperationService;
import org.cbrf.util.UtilService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Slf4j
public class Main {

    private static final List<Client> clients = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);
    private static final ClientService clientService = new ClientService(scanner);
    private static final AccountService accountService = new AccountService(clientService, scanner);
    private static final OperationService operationService = new OperationService(accountService, clientService, scanner);
    private static final UtilService utilService = new UtilService(scanner);

    public static void main(String[] args) {
        while (true) {
            System.out.println("Выберите действие:");
            System.out.println("1. Создать клиента");
            System.out.println("2. Создать счет клиенту");
            System.out.println("3. Закрыть счет клиенту");
            System.out.println("4. Перевести денежные средства");
            System.out.println("5. Зачислить денежные средства");
            System.out.println("0. Выйти");
            System.out.print("Введите номер действия: ");

            try {
                switch (utilService.getChoice()) {
                    case 1 -> clientService.createClient(clients);
                    case 2 -> accountService.createAccountForClient(clients);
                    case 3 -> accountService.closeAccountForClient(clients);
                    case 4 -> operationService.transferFunds(clients);
                    case 5 -> operationService.depositFunds(clients);
                    case 0 -> {
                        System.out.println("Выход из программы.");
                        return;
                    }
                }
            }catch (RuntimeException e){
                log.info(e.getMessage());
            }
        }
    }
}