package ru.lab5.DAO;

import ru.lab5.Entities.Tour;
import ru.lab5.POJO.TourDTO;

import java.util.List;

public interface ITourDao {
    List<Tour> selectAll();

    boolean update(TourDTO tour);

    Tour selectByPK(int idTour);

    boolean deleteById(int idTour);

    boolean insert(TourDTO tour);
}
