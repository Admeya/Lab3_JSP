package ru.lab5.DAO;

import ru.lab5.Entities.Country;
import ru.lab5.POJO.CountryDTO;

import java.util.List;

public interface ICountryDao {
    List<Country> selectAll();

    boolean update(CountryDTO country);

    Country selectByPK(int idCountry);

    boolean deleteById(int idCountry);

    boolean insert(CountryDTO country);

}
