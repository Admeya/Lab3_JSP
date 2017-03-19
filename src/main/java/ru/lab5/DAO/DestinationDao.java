package ru.lab5.DAO;

import org.springframework.stereotype.Repository;
import ru.lab5.Entities.Destination;
import ru.lab5.POJO.DestinationDTO;

import java.util.List;
@Repository
public class DestinationDao implements IDestinationDao {

    @Override
    public Destination selectByPK(int idDest) {
        return null;
    }

    @Override
    public boolean update(DestinationDTO destination) {
        return false;
    }

    @Override
    public List<Destination> selectAll() {
        return null;
    }

    @Override
    public boolean deleteById(int idDest) {
        return false;
    }

    @Override
    public boolean insert(DestinationDTO destination) {
        return false;
    }
}
