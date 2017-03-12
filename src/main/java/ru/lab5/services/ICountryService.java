package ru.lab5.services;

import ru.lab5.Entities.CountryEntity;
import ru.lab5.Entities.TourEntity;

import java.util.List;

/**
 * Created by Ирина on 02.03.2017.
 */
public interface ICountryService {

    List<CountryEntity> getAllCountries();

    boolean updateCountry(CountryEntity tour);

    boolean deleteCountry(int countryId);

    CountryEntity getCountryByID(int idCountry);

    boolean addCountry(CountryEntity country);
}
