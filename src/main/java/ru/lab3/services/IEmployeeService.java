package ru.lab3.services;

import ru.lab3.Entities.EmployeeEntity;
import ru.lab3.common.EmployeeDAOException;

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
