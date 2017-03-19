package ru.lab5.DAO;

import org.springframework.stereotype.Repository;
import ru.lab5.Entities.Client;
import ru.lab5.Entities.Employee;
import ru.lab5.POJO.EmployeeDTO;
import ru.lab5.common.SaltPassword;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public interface IEmployeeDao {
    List<Employee> selectByLogin(String login);

    Employee selectByPK(int idEmployee);

    boolean update(EmployeeDTO employee);

    List<Employee> selectAll();

    boolean deleteById(int idEmployee);

    boolean insert(EmployeeDTO employee);

    int getIdByName(String name);
}
