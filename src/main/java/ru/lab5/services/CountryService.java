package ru.lab5.services;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.lab5.DAO.CountryDao;
import ru.lab5.DAO.ICountryDao;
import ru.lab5.Entities.Country;
import ru.lab5.POJO.CountryDTO;
import ru.lab5.common.ConnectionPool;
import ru.lab5.controllers.foradmin.AddEmployeeController;

import java.sql.Connection;
import java.util.List;

/**
 * Created by Ирина on 24.02.2017.
 */
@Service
public class CountryService implements ICountryService {
    static Connection conn = ConnectionPool.getInstance().getConnection();
    private static Logger logger = Logger.getLogger(AddEmployeeController.class);

    private ICountryDao countryDao;

    @Autowired
    public CountryService(CountryDao countryDao) {
        this.countryDao = countryDao;
    }

    @Override
    public List<Country> getAllCountries() {
        return countryDao.selectAll();
    }

    public boolean deleteCountry(int countryId) {
        return countryDao.deleteById(countryId);
    }

    @Override
    public boolean updateCountry(CountryDTO country) {
        return countryDao.update(country);
    }

    @Override
    public Country getCountryByID(int idCountry) {
        return countryDao.selectByPK(idCountry);
    }

    @Override
    public boolean addCountry(CountryDTO country) {
        return countryDao.insert(country);
    }
}
