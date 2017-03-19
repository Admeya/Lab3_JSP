package ru.lab5.DAO;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import ru.lab5.Entities.Client;
import ru.lab5.Entities.Employee;
import ru.lab5.POJO.ClientDTO;
import ru.lab5.POJO.EmployeeDTO;
import ru.lab5.common.SaltPassword;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

@Repository
public class EmployeeDao implements IEmployeeDao {
    private static final EntityManagerFactory FACTORY = Persistence.createEntityManagerFactory("TOUR");
    Logger logger = Logger.getLogger(ClientDao.class);

    public List<Employee> selectByLogin(String login) {
        EntityManager em = FACTORY.createEntityManager();
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);
        Root<Employee> root = criteriaQuery.from(Employee.class);
        criteriaQuery.select(root);
        criteriaQuery.where(
                criteriaBuilder.and(
                        criteriaBuilder.equal(root.get("login"), login)
                )
        );
        List<Employee> users = em.createQuery(criteriaQuery).getResultList();
        logger.trace("I find client! " + users.get(0));
        return users;
    }

    public Employee selectByPK(int idEmployee) {
        EntityManager em = FACTORY.createEntityManager();
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);
        Root<Employee> root = criteriaQuery.from(Employee.class);
        criteriaQuery.select(root);
        criteriaQuery.where(
                criteriaBuilder.and(
                        criteriaBuilder.equal(root.get("idEmployee"), idEmployee)
                )
        );
        List<Employee> users = em.createQuery(criteriaQuery).getResultList();
        logger.trace("I find client! " + users.get(0));
        return users.get(0);
    }

    public boolean update(EmployeeDTO employee) {
        EntityManager em = FACTORY.createEntityManager();
        em.getTransaction().begin();

        Employee employeeEntity = selectByPK(employee.getIdEmployee());

        employeeEntity.setName(employee.getName().trim());
        employeeEntity.setSurname(employee.getSurname().trim());
        employeeEntity.setPhone(employee.getPhone().trim());
        employeeEntity.setEmail(employee.getEmail());
        employeeEntity.setLogin(employee.getLogin().trim());
        employeeEntity.setRole(employee.getRole().trim());

        em.merge(employeeEntity);
        em.getTransaction().commit();
        em.close();
        return true;
    }

    public List<Employee> selectAll() {
        EntityManager em = FACTORY.createEntityManager();
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);
        Root<Employee> root = criteriaQuery.from(Employee.class);
        criteriaQuery.select(root);
        List<Employee> users = em.createQuery(criteriaQuery).getResultList();
        return users;
    }

    public boolean deleteById(int idEmployee) {
        return true;
    }

    @Override
    public boolean insert(EmployeeDTO employee) {
        return false;
    }

    @Override
    public int getIdByName(String name) {
        EntityManager em = FACTORY.createEntityManager();
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);
        Root<Employee> root = criteriaQuery.from(Employee.class);
        criteriaQuery.select(root);
        criteriaQuery.where(
                criteriaBuilder.and(
                        criteriaBuilder.equal(root.get("name"), name)
                )
        );
        List<Employee> users = em.createQuery(criteriaQuery).getResultList();
        logger.trace("I find client! " + users.get(0));
        return users.get(0).getIdEmployee();
    }


}
