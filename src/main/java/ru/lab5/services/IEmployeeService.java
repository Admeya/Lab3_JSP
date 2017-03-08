package ru.lab5.services;

import ru.lab5.Entities.EmployeeEntity;
import ru.lab5.exceptions.EmployeeDAOException;

import java.util.List;

/**
 * Created by Ирина on 02.03.2017.
 */
public interface IEmployeeService {
    EmployeeEntity isAuthorize(String login, String pass) throws EmployeeDAOException;

    EmployeeEntity getClientByID(int idJournal);

    boolean updateEmpl(EmployeeEntity cli);

    List<EmployeeEntity> selectAll();

    boolean addEmployee(EmployeeEntity employee);

    boolean deleteEmployee(int empId);

    int getIdByParam(String column, String value);
}
