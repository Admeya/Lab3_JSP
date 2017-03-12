package ru.lab5.services;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.lab5.DAO.CountryDao;
import ru.lab5.DAO.TourDao;
import ru.lab5.Entities.CountryEntity;
import ru.lab5.Entities.EmployeeEntity;
import ru.lab5.Entities.TourEntity;
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

    private CountryDao countryDao;

    @Autowired
    public CountryService(CountryDao countryDao) {
        countryDao.setConnection(conn);
        this.countryDao = countryDao;
    }

    @Override
    public List<CountryEntity> getAllCountries() {
        return countryDao.selectAll();
    }

    public boolean deleteCountry(int countryId) {
        return countryDao.deleteById(CountryEntity.columnId, countryId);
    }

    @Override
    public boolean updateCountry(CountryEntity country) {
        return countryDao.update(country);
    }

    @Override
    public CountryEntity getCountryByID(int idCountry) {
        return countryDao.selectByPK(idCountry, CountryEntity.columnId, new CountryEntity());
    }

    @Override
    public boolean addCountry(CountryEntity country) {
        return countryDao.insert(country);
    }
}
