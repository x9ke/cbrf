package org.cbrf.service.client;

import org.cbrf.model.Client;

import java.util.List;

/**
 * Интерфейс сервиса для управления данными клиентов.
 */
public interface ClientService {

    /**
     * Создает новых клиентов.
     *
     * @param clients список клиентов.
     */
    void createClient(List<Client> clients);

    /**
     * Выбирает клиента из предоставленного списка.
     *
     * @param clients список клиентов, из которого необходимо выбрать клиента.
     * @return выбранный клиент из списка
     */
    Client selectClient(List<Client> clients);
}
