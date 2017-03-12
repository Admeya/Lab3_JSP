package ru.lab5.services;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.lab5.DAO.CountryDao;
import ru.lab5.DAO.DestinationDao;
import ru.lab5.Entities.DestinationEntity;
import ru.lab5.common.ConnectionPool;
import ru.lab5.controllers.foradmin.AddEmployeeController;

import java.sql.Connection;
import java.util.List;

/**
 * Created by Ирина on 02.03.2017.
 */
@Service
public class DestinationService implements IDestinationService {
    static Connection conn = ConnectionPool.getInstance().getConnection();
    private static Logger logger = Logger.getLogger(DestinationService.class);

    private DestinationDao destinationDao;

    @Autowired
    public DestinationService(DestinationDao destinationDao) {
        destinationDao.setConnection(conn);
        this.destinationDao = destinationDao;
    }

    public List<DestinationEntity> getAllDests() {
        return destinationDao.selectAll();
    }

    public boolean updateDestination(DestinationEntity dest) {
        return destinationDao.update(dest);
    }

    public boolean deleteDestination(int destId) {
        return destinationDao.deleteById(DestinationEntity.columnId, destId);
    }

    public DestinationEntity getDestByID(int destId) {
        return destinationDao.selectByPK(destId, DestinationEntity.columnId, new DestinationEntity());
    }

    public boolean addDestination(DestinationEntity dest) {
        return destinationDao.insert(dest);
    }
}
