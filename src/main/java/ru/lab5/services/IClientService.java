package ru.lab5.services;

import ru.lab5.Entities.Client;
import ru.lab5.POJO.ClientDTO;
import ru.lab5.exceptions.ClientDAOException;

/**
 * Created by Ирина on 02.03.2017.
 */
public interface IClientService {
    // Client authorize(String login, String pass) throws ClientDAOException;

    boolean registration(Client client);

    Client getClientByID(int idJournal);

    boolean updateClient(ClientDTO cli);

    Client authorize(String login);
}
