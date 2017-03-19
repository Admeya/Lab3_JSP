package ru.lab5.DAO;

import ru.lab5.Entities.Client;
import ru.lab5.POJO.ClientDTO;

import java.util.List;

public interface IClientDao {
    public List<Client> selectByLogin(String login);

    public Client selectByPK(int idClient);

    boolean editClient(ClientDTO client);
}
