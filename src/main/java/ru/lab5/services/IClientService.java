package ru.lab5.services;

import ru.lab5.Entities.ClientEntity;
import ru.lab5.exceptions.ClientDAOException;

/**
 * Created by Ирина on 02.03.2017.
 */
public interface IClientService {
    ClientEntity isAuthorize(String login, String pass) throws ClientDAOException;

    boolean registration(ClientEntity client);

    ClientEntity getClientByID(int idJournal);

    boolean updateClient(ClientEntity cli);

    ClientEntity authorize(String login);
}
