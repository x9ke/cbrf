package org.cbrf.service.account;

import org.cbrf.model.Account;
import org.cbrf.model.Client;

import java.util.List;

/**
 * Интерфейс сервиса для управления счетами клиентов.
 */
public interface AccountService {

    /**
     * Создает счета для списка клиентов.
     *
     * @param clients список клиентов, для которых необходимо создать счета.
     *
     */
    void createAccount(List<Client> clients);

    /**
     * Закрывает счета для списка клиентов.
     *
     * @param clients список клиентов, чьи счета необходимо закрыть.
     *
     */
    void closeAccount(List<Client> clients);

    /**
     * Находит открытый счет для указанного клиента.
     *
     * @param client клиент, для которого требуется найти открытый счет.
     * @return открытый счет.
     */
    Account selectOpenAccount(Client client);
}

