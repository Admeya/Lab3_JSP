package ru.lab5.services;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.lab5.DAO.TourDao;
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

    private TourDao tourDAO;

    @Autowired
    public TourService(TourDao tourDAO) {
        this.tourDAO = tourDAO;
    }

    @Override
    public List<TourEntity> getAllTours() {
        tourDAO.setConnection(conn);
        return tourDAO.selectAllTours();
    }

    @Override
    public boolean updateTour(TourEntity tour) {
        return false;
    }
}
