package ru.lab5.DAO;

import ru.lab5.Entities.Destination;
import ru.lab5.POJO.DestinationDTO;

import java.util.List;

public interface IDestinationDao {
    Destination selectByPK(int idDest);

    boolean update(DestinationDTO destination);

    List<Destination> selectAll();

    boolean deleteById(int idDest);

    boolean insert(DestinationDTO destination);

}
