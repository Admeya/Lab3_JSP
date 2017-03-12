package ru.lab5.services;

import org.springframework.beans.factory.annotation.Autowired;
import ru.lab5.DAO.GenericDAO;
import ru.lab5.Entities.EmployeeEntity;
import ru.lab5.common.ConnectionPool;
import ru.lab5.exceptions.EmployeeDAOException;
import ru.lab5.controllers.foradmin.AddEmployeeController;
import ru.lab5.DAO.EmployeeDao;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.util.List;

/**
 * Created by Ирина on 24.02.2017.
 */
@Service
public class EmployeeService implements IEmployeeService {
    static Connection conn = ConnectionPool.getInstance().getConnection();
    private static Logger logger = Logger.getLogger(AddEmployeeController.class);

    static {
        PropertyConfigurator.configure("/src/main/resources/log4j.xml");
    }

    private GenericDAO<EmployeeEntity> employeeDao;

    @Autowired
    public EmployeeService(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    public EmployeeEntity isAuthorize(String login, String pass) throws EmployeeDAOException {
        logger.trace(conn + "Connection");
        employeeDao.setConnection(conn);
        List<EmployeeEntity> list = employeeDao.selectByLoginAndPassword(login, pass);
        EmployeeEntity clEntity = null;
        if (list != null) {
            clEntity = list.get(0);
        } else
            return null;
        return clEntity;
    }

    public EmployeeEntity getClientByID(int idJournal) {
        employeeDao.setConnection(conn);
        return employeeDao.selectByPK(idJournal, EmployeeEntity.columnId, new EmployeeEntity());
    }

    public boolean updateEmpl(EmployeeEntity cli) {
        employeeDao.setConnection(conn);
        return employeeDao.update(cli);
    }

    public List<EmployeeEntity> selectAll() {
        employeeDao.setConnection(conn);
        return employeeDao.selectAll();
    }

    public boolean addEmployee(EmployeeEntity employee) {
        employeeDao.setConnection(conn);
        return employeeDao.insert(employee);
    }

    public boolean deleteEmployee(int empId) {
        employeeDao.setConnection(conn);
        return employeeDao.deleteById(EmployeeEntity.columnId, empId);
    }

    @Override
    public int getIdByParam(String column, String value) {
        employeeDao.setConnection(conn);
        return employeeDao.selectIdByParam(column, value);
    }
}
