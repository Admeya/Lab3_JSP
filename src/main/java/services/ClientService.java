package services;

import Entities.ClientEntity;
import common.ClientDAOException;
import common.ConnectionPool;
import common.ConnectionSingleton;
import controllers.AddEmployeeServlet;
import model.DAO.ClientDao;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.sql.Connection;
import java.util.List;

/**
 * Created by Ирина on 24.02.2017.
 */
public class ClientService {
    static Connection conn = ConnectionSingleton.getInstance().getConnection();
    private static Logger logger = Logger.getLogger(AddEmployeeServlet.class);

    // static Connection conn = ConnectionPool.getConnection();

    public static ClientEntity isAuthorize(String login, String pass) throws ClientDAOException {
        logger.trace(conn + "Connection");
        ClientDao client = new ClientDao(conn);
        List<ClientEntity> list = client.selectByLoginAndPassword(login, pass);
        ClientEntity clEntity = null;
        if (list != null) {
            clEntity = list.get(0);
        } else
            return null;
        return clEntity;
    }

    public static boolean registration(ClientEntity client) {
        ClientDao clientDao = new ClientDao(conn);
        return clientDao.insert(client);
    }

    public static ClientEntity getClientByID(int idJournal) {
        ClientDao clientDao = new ClientDao(conn);
        return clientDao.selectByPK(idJournal, ClientEntity.columnIdClient, new ClientEntity());
    }

    public static boolean updateClient(ClientEntity cli) {
        ClientDao clientDao = new ClientDao(conn);
        return clientDao.update(cli);
    }
}
