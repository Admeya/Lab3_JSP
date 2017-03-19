package ru.lab5.DAO;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import ru.lab5.Entities.Client;
import ru.lab5.POJO.ClientDTO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Component
public class ClientDao implements IClientDao {
    private static final EntityManagerFactory FACTORY = Persistence.createEntityManagerFactory("TOUR");
    Logger logger = Logger.getLogger(ClientDao.class);

    public List<Client> selectByLogin(String login) {
        EntityManager em = FACTORY.createEntityManager();
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Client> criteriaQuery = criteriaBuilder.createQuery(Client.class);
        Root<Client> root = criteriaQuery.from(Client.class);
        criteriaQuery.select(root);
        criteriaQuery.where(
                criteriaBuilder.and(
                        criteriaBuilder.equal(root.get("login"), login)
                )
        );
        List<Client> users = em.createQuery(criteriaQuery).getResultList();
        logger.trace("I find client! " + users.get(0));
        return users;
    }

    public Client selectByPK(int idClient) {
        EntityManager em = FACTORY.createEntityManager();
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Client> criteriaQuery = criteriaBuilder.createQuery(Client.class);
        Root<Client> root = criteriaQuery.from(Client.class);
        criteriaQuery.select(root);
        criteriaQuery.where(
                criteriaBuilder.and(
                        criteriaBuilder.equal(root.get("idClient"), idClient)
                )
        );
        List<Client> users = em.createQuery(criteriaQuery).getResultList();
        logger.trace("I find client! " + users.get(0));
        return users.get(0);
    }

    public boolean editClient(ClientDTO client) {
        logger.trace(client + " our client");
        EntityManager em = FACTORY.createEntityManager();
        em.getTransaction().begin();

        Client clientEntity = selectByPK(client.getIdClient());

        clientEntity.setName(client.getName().trim());
        clientEntity.setSurname(client.getSurname().trim());
        clientEntity.setMiddlename(client.getMiddlename().trim());
        clientEntity.setBirthdate(client.getBirthdate());
        clientEntity.setLogin(client.getLogin().trim());
        clientEntity.setPassportSerNum(client.getPassportSerNum().trim());
        clientEntity.setPhone(client.getPhone().trim());

        em.merge(clientEntity);
        em.getTransaction().commit();
        em.close();
        return true;
    }
}
