package dao;

import models.City;
import models.Country;

import java.util.List;

public interface CityDAO {
    boolean addCity(City city);
    boolean updateCity(City city);
    boolean deleteCity(City city);
    City getCityById(long id);
    City getCityByName(String name);
    List<City> getCitiesByCountry(Country country);
    List<City> getAllCities();
}
