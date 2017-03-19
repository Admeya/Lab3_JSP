package ru.lab5.DAO;

import org.springframework.stereotype.Repository;
import ru.lab5.Entities.Country;
import ru.lab5.Entities.Destination;
import ru.lab5.Entities.Tour;
import ru.lab5.POJO.TourDTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

@Repository
public class TourDao implements ITourDao {

    @Override
    public List<Tour> selectAll() {
        return null;
    }

    @Override
    public boolean update(TourDTO tour) {
        return false;
    }

    @Override
    public Tour selectByPK(int idTour) {
        return null;
    }

    @Override
    public boolean deleteById(int idTour) {
        return false;
    }

    @Override
    public boolean insert(TourDTO tour) {
        return false;
    }
}
