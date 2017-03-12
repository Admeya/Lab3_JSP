package ru.lab5.services;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.lab5.DAO.TourDao;
import ru.lab5.Entities.DestinationEntity;
import ru.lab5.Entities.TourEntity;
import ru.lab5.common.ConnectionPool;
import ru.lab5.controllers.foradmin.AddEmployeeController;

import java.sql.Connection;
import java.util.List;

/**
 * Created by Ирина on 24.02.2017.
 */
@Service
public class TourService implements ITourService {
    static Connection conn = ConnectionPool.getInstance().getConnection();
    private static Logger logger = Logger.getLogger(AddEmployeeController.class);

    private TourDao tourDao;

    @Autowired
    public TourService(TourDao tourDao) {
        this.tourDao = tourDao;
    }

    @Override
    public List<TourEntity> getAllTours() {
        tourDao.setConnection(conn);
        return tourDao.selectAll();
    }

    @Override
    public boolean updateTour(TourEntity tour) {
        return tourDao.update(tour);
    }

    @Override
    public boolean addTour(TourEntity tour) {
        return tourDao.insert(tour);
    }

    @Override
    public boolean deleteTour(int idTour) {
        return tourDao.deleteById(TourEntity.columnId, idTour);
    }

    @Override
    public TourEntity getTourById(int idTour) {
        return tourDao.selectByPK(idTour, TourEntity.columnId, new TourEntity());
    }
}
