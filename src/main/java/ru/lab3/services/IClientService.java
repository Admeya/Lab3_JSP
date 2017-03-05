package ru.lab3.services;

import ru.lab3.Entities.ClientEntity;
import ru.lab3.common.ClientDAOException;

/**
 * Created by Ирина on 02.03.2017.
 */
public interface IClientService {
    ClientEntity isAuthorize(String login, String pass) throws ClientDAOException;

    boolean registration(ClientEntity client);

    ClientEntity getClientByID(int idJournal);

    boolean updateClient(ClientEntity cli);
}
