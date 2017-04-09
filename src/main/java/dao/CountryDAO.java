package dao;

import models.Country;

import java.util.List;

public interface CountryDAO {
    boolean addCountry(Country country);
    boolean updateCountry(Country country);
    boolean deleteCountry(Country country);
    Country getCountryById(long id);
    Country getCountryByName(String name);
    List<Country> getAllCountries();
}
