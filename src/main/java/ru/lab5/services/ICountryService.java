package ru.lab5.services;

import ru.lab5.Entities.Country;
import ru.lab5.POJO.CountryDTO;

import java.util.List;

/**
 * Created by Ирина on 02.03.2017.
 */
public interface ICountryService {

    List<Country> getAllCountries();

    boolean updateCountry(CountryDTO country);

    boolean deleteCountry(int countryId);

    Country getCountryByID(int idCountry);

    boolean addCountry(CountryDTO country);
}
