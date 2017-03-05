package ru.lab3.services;

import ru.lab3.Entities.ClientEntity;
import ru.lab3.Entities.TourEntity;
import ru.lab3.common.ClientDAOException;

import java.util.List;

/**
 * Created by Ирина on 02.03.2017.
 */
public interface ITourService {

    List<TourEntity> getAllTours();

    boolean updateTour(TourEntity tour);
}
