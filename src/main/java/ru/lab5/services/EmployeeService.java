package ru.lab5.services;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.lab5.DAO.EmployeeDao;
import ru.lab5.DAO.IEmployeeDao;
import ru.lab5.Entities.Employee;
import ru.lab5.POJO.EmployeeDTO;
import ru.lab5.controllers.foradmin.AddEmployeeController;

import java.util.List;

/**
 * Created by Ирина on 24.02.2017.
 */
@Service
public class EmployeeService implements IEmployeeService {
    private static Logger logger = Logger.getLogger(AddEmployeeController.class);

    private IEmployeeDao employeeDao;

    @Autowired
    public EmployeeService(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    public Employee authorize(String login) {
        return employeeDao.selectByLogin(login).get(0);
    }

    public Employee getClientByID(int idEmployee) {
        return employeeDao.selectByPK(idEmployee);
    }

    public boolean updateEmpl(EmployeeDTO cli) {
        return employeeDao.update(cli);
    }

    public List<Employee> selectAll() {
        return employeeDao.selectAll();
    }

    @Override
    public int getIdByName(String name) {
        return employeeDao.getIdByName(name);
    }

    public boolean addEmployee(EmployeeDTO employee) {
        return employeeDao.insert(employee);
    }

    public boolean deleteEmployee(int empId) {
        return employeeDao.deleteById(empId);
    }

}
