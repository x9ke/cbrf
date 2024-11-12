package org.cbrf;

import lombok.extern.slf4j.Slf4j;

import org.cbrf.model.Client;
import org.cbrf.service.account.AccountService;
import org.cbrf.service.account.AccountServiceImpl;
import org.cbrf.service.client.ClientService;
import org.cbrf.service.client.ClientServiceImpl;
import org.cbrf.service.operation.OperationService;
import org.cbrf.service.operation.OperationServiceImpl;
import org.cbrf.util.UtilService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Slf4j
public class BankApplication {
    private static final List<Client> clients = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);
    private static final ClientService clientService = new ClientServiceImpl(scanner);
    private static final AccountService accountService = new AccountServiceImpl(clientService, scanner);
    private static final OperationService operationService = new OperationServiceImpl(accountService, clientService, scanner);
    private static final UtilService utilService = new UtilService(scanner);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n\n");
            System.out.println("****************************************");
            System.out.println("           МЕНЮ БАНКОВСКИХ ОПЕРАЦИЙ        ");
            System.out.println("****************************************");
            System.out.println();
            System.out.println("    [1] Создать клиента");
            System.out.println("    [2] Создать счет клиенту");
            System.out.println("    [3] Закрыть счет клиенту");
            System.out.println("    [4] Перевести денежные средства");
            System.out.println("    [5] Зачислить денежные средства");
            System.out.println("    [0] Выйти");
            System.out.println();
            System.out.print("Введите номер действия (0-5): ");

            try {
                switch (utilService.getChoice()) {
                    case 1 -> clientService.createClient(clients);
                    case 2 -> accountService.createAccount(clients);
                    case 3 -> accountService.closeAccount(clients);
                    case 4 -> operationService.transfer(clients);
                    case 5 -> operationService.deposit(clients);
                    case 0 -> {
                        System.out.println("\n***************************************");
                        System.out.println("           ВЫХОД ИЗ ПРОГРАММЫ          ");
                        System.out.println("***************************************");
                        scanner.close();
                        return;
                    }
                }
            }catch (RuntimeException e){
                log.info("[OnlineBankService]: {} " , e.getMessage());
            }
        }
    }
}