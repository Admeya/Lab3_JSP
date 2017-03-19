package ru.lab5.services;

import ru.lab5.Entities.Tour;
import ru.lab5.POJO.TourDTO;

import java.util.List;

/**
 * Created by Ирина on 02.03.2017.
 */
public interface ITourService {

    List<Tour> getAllTours();

    boolean updateTour(TourDTO tour);

    boolean addTour(TourDTO tour);

    boolean deleteTour(int tourId);

    Tour getTourById(int idDest);
}
