package ru.lab5.services;

import ru.lab5.Entities.TourEntity;

import java.util.List;

/**
 * Created by Ирина on 02.03.2017.
 */
public interface ITourService {

    List<TourEntity> getAllTours();

    boolean updateTour(TourEntity tour);
}
