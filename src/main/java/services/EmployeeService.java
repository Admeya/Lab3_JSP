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
    static ConnectionPool conpul = new ConnectionPool();
    static Connection conn = conpul.getConnection();
    private static Logger logger = Logger.getLogger(AddEmployeeServlet.class);

    static {
        PropertyConfigurator.configure("/src/main/resources/log4j.xml");
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

    public static EmployeeEntity getClientByID(int idJournal) {
        EmployeeDao employeeDao = new EmployeeDao(conn);
        return employeeDao.selectByPK(idJournal, EmployeeEntity.columnId, new EmployeeEntity());
    }

    public static boolean updateEmpl(EmployeeEntity cli) {
        EmployeeDao employeeDao = new EmployeeDao(conn);
        return employeeDao.update(cli);
    }

    public static List<EmployeeEntity> selectAll() {
        EmployeeDao employeeDao = new EmployeeDao(conn);
        return employeeDao.selectAll();
    }

    public static boolean addEmployee(EmployeeEntity employee) {
        EmployeeDao employeeDao = new EmployeeDao(conn);
        return employeeDao.insert(employee);
    }


    public static boolean deleteEmployee(int empId) {
        EmployeeDao employeeDao = new EmployeeDao(conn);
        return employeeDao.deleteById(EmployeeEntity.columnId, empId);
    }
}
