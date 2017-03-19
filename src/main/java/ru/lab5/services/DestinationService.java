package ru.lab5.services;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.lab5.DAO.DestinationDao;
import ru.lab5.DAO.IDestinationDao;
import ru.lab5.Entities.Destination;
import ru.lab5.POJO.DestinationDTO;

import java.util.List;

/**
 * Created by Ирина on 02.03.2017.
 */
@Service
public class DestinationService implements IDestinationService {
    private static Logger logger = Logger.getLogger(DestinationService.class);

    private IDestinationDao destinationDao;

    @Autowired
    public DestinationService(DestinationDao destinationDao) {
        this.destinationDao = destinationDao;
    }

    public List<Destination> getAllDests() {
        return destinationDao.selectAll();
    }

    public boolean updateDestination(DestinationDTO dest) {
        return destinationDao.update(dest);
    }

    public boolean deleteDestination(int destId) {
        return destinationDao.deleteById(destId);
    }

    public Destination getDestByID(int destId) {
        return destinationDao.selectByPK(destId);
    }

    public boolean addDestination(DestinationDTO dest) {
        return destinationDao.insert(dest);
    }
}
