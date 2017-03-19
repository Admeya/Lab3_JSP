package ru.lab5.services;

import ru.lab5.Entities.Employee;
import ru.lab5.POJO.EmployeeDTO;
import ru.lab5.exceptions.EmployeeDAOException;

import java.util.List;

/**
 * Created by Ирина on 02.03.2017.
 */
public interface IEmployeeService {
    Employee authorize(String login);

    Employee getClientByID(int idJournal);

    boolean updateEmpl(EmployeeDTO cli);

    List<Employee> selectAll();

    int getIdByName(String name);

    boolean addEmployee(EmployeeDTO employee);

    boolean deleteEmployee(int empId);
}
