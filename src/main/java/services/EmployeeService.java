package services;

import Entities.EmployeeEntity;
import Entities.EmployeeEntity;
import common.ClientDAOException;
import common.ConnectionPool;
import common.ConnectionSingleton;
import common.EmployeeDAOException;
import controllers.AddEmployeeServlet;
import model.DAO.EmployeeDao;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.sql.Connection;
import java.util.List;

/**
 * Created by Ирина on 24.02.2017.
 */
public class EmployeeService {
    static Connection conn = ConnectionSingleton.getInstance().getConnection();
    private static Logger logger = Logger.getLogger(AddEmployeeServlet.class);

    static {
        PropertyConfigurator.configure("./src/main/resources/log4j.xml");
    }

    public static EmployeeEntity isAuthorize(String login, String pass) throws EmployeeDAOException {
        logger.trace(conn + "Connection");
        EmployeeDao employee = new EmployeeDao(conn);
        List<EmployeeEntity> list = employee.selectByLoginAndPassword(login, pass);
        EmployeeEntity clEntity = null;
        if (list != null) {
            clEntity = list.get(0);
        } else
            return null;
        return clEntity;
    }

    public static boolean registration(EmployeeEntity client) {
        EmployeeDao employeeDao = new EmployeeDao(conn);
        return employeeDao.insert(client);
    }

    public static EmployeeEntity getClientByID(int idJournal) {
        EmployeeDao clientDao = new EmployeeDao(conn);
        return clientDao.selectByPK(idJournal, EmployeeEntity.columnId, new EmployeeEntity());
    }

    public static boolean updateClient(EmployeeEntity cli) {
        EmployeeDao clientDao = new EmployeeDao(conn);
        return clientDao.update(cli);
    }

    public static List<EmployeeEntity> selectAll() {
        EmployeeDao employeeDao = new EmployeeDao(conn);
        return employeeDao.selectAll();
    }

    public static boolean addEmployee(EmployeeEntity employee) {
        EmployeeDao employeeDao = new EmployeeDao(conn);
        return employeeDao.insert(employee);
    }
}