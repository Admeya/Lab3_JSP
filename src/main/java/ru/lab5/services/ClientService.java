package ru.lab5.services;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.lab5.DAO.ClientDao;
import ru.lab5.DAO.GenericDAO;
import ru.lab5.DAO.IClientDao;
import ru.lab5.Entities.Client;
import ru.lab5.POJO.ClientDTO;
import ru.lab5.exceptions.ClientDAOException;
import ru.lab5.common.ConnectionPool;
import ru.lab5.controllers.foradmin.AddEmployeeController;

import java.sql.Connection;
import java.util.List;

/**
 * Created by Ирина on 24.02.2017.
 */
@Service
public class ClientService implements IClientService {
    static Connection conn = ConnectionPool.getInstance().getConnection();
    private static Logger logger = Logger.getLogger(AddEmployeeController.class);

    private IClientDao clientDAO;

    @Autowired
    public ClientService(ClientDao clientDAO) {
        this.clientDAO = clientDAO;
    }

    public Client authorize(String login) {
        List<Client> list = clientDAO.selectByLogin(login);
        Client clEntity = null;
        if (list != null) {
            clEntity = list.get(0);
        } else
            return null;
        return clEntity;
    }

    public boolean registration(Client client) {
        return true; //clientDAO.insert(client);
    }

    public Client getClientByID(int idJournal) {
        return clientDAO.selectByPK(idJournal);
    }

    public boolean updateClient(ClientDTO cli) {
        return clientDAO.editClient(cli);
    }
}
