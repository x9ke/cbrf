package org.cbrf.service.operation;

import org.cbrf.model.Client;

import java.util.List;

/**
 * Интерфейс сервиса для выполнения операций с клиентскими счетами.
 */
public interface OperationService {

    /**
     * Выполняет перевод средств между счетами клиентов.
     *
     * @param clients список клиентов, участвующих в операции перевода.
     *                Включает отправителя и получателя средств.
     */
    void transfer(List<Client> clients);

    /**
     * Выполняет операцию пополнения счета для списка клиентов.
     *
     * @param clients список клиентов, для которых необходимо пополнить счета.
     */
    void deposit(List<Client> clients);
}
