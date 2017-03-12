package ru.lab5.services;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.lab5.DAO.ClientDao;
import ru.lab5.DAO.GenericDAO;
import ru.lab5.Entities.ClientEntity;
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
    private GenericDAO<ClientEntity> clientDAO;

    @Autowired
    public ClientService(ClientDao clientDAO) {
        this.clientDAO = clientDAO;
    }

    public ClientEntity isAuthorize(String login, String pass) throws ClientDAOException {
        logger.trace(conn + "Connection");
        clientDAO.setConnection(conn);
        List<ClientEntity> list = clientDAO.selectByLoginAndPassword(login, pass);
        ClientEntity clEntity = null;
        if (list != null) {
            clEntity = list.get(0);
        } else
            return null;
        return clEntity;
    }

    public boolean registration(ClientEntity client) {
        clientDAO.setConnection(conn);
        return clientDAO.insert(client);
    }

    public ClientEntity getClientByID(int idJournal) {
        clientDAO.setConnection(conn);
        return clientDAO.selectByPK(idJournal, ClientEntity.columnIdClient, new ClientEntity());
    }

    public boolean updateClient(ClientEntity cli) {
        clientDAO.setConnection(conn);
        return clientDAO.update(cli);
    }
}