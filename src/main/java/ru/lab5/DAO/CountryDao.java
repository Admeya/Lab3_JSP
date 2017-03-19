package ru.lab5.DAO;

import org.springframework.stereotype.Repository;
import ru.lab5.Entities.Country;
import ru.lab5.Entities.Tour;
import ru.lab5.POJO.CountryDTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

@Repository
public class CountryDao implements ICountryDao {

    @Override
    public List<Country> selectAll() {
        return null;
    }

    @Override
    public boolean update(CountryDTO country) {
        return false;
    }

    @Override
    public Country selectByPK(int idCountry) {
        return null;
    }

    @Override
    public boolean deleteById(int idCountry) {
        return false;
    }

    @Override
    public boolean insert(CountryDTO country) {
        return false;
    }
}
