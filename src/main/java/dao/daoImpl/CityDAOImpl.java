package dao.daoImpl;

import dao.CityDAO;
import dao.CountryDAO;
import dao.DAOFactory;
import models.City;
import models.Country;
import util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CityDAOImpl implements CityDAO {
    public boolean addCity(City city) {
        boolean flag = true;
        Connection connection = JDBCUtil.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO cities" +
                                                                    "(city_id, fullname, country_id) " +
                                                                    "VALUES (cities_seq.NEXTVAL, ?, ?)");
            ps.setString(1, city.getName());
            ps.setLong(2, city.getCountry().getId());
            ps.executeUpdate();
        } catch (SQLException ex){
            flag = false;
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex){
                }
            }
        }
        return flag;
    }

    public boolean updateCity(City city) {
        boolean flag = true;
        Connection connection = JDBCUtil.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE cities SET " +
                    "fullname = ?," +
                    "country_id = ? " +
                    "WHERE city_id = ?");
            ps.setString(1, city.getName());
            ps.setLong(2, city.getCountry().getId());
            ps.setLong(3, city.getId());
            ps.executeUpdate();
        } catch (SQLException ex){
            flag = false;
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex){
                }
            }
        }
        return flag;
    }

    public boolean deleteCity(City city) {
        boolean flag = true;
        Connection connection = JDBCUtil.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM cities WHERE city_id = ?");
            ps.setLong(1, city.getId());
            ps.executeUpdate();
        } catch (SQLException ex){
            flag = false;
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex){
                }
            }
        }
        return flag;
    }

    public City getCityById(long id) {
        City city = new City();
        CountryDAO countryDAO = DAOFactory.getInstance().getCountryDAO();
        Connection connection = JDBCUtil.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM cities WHERE city_id = ?");
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            rs.next();
            city.setId(id);
            city.setName(rs.getString("fullname"));
            city.setCountry(countryDAO.getCountryById(rs.getLong("country_id")));
        } catch (SQLException ex){
            city = new City();
        } finally {
            if (connection != null){
                try {
                    connection.close();
                } catch (SQLException ex){
                }
            }
        }
        return city;
    }

    public City getCityByName(String name) {
        City city = new City();
        CountryDAO countryDAO = DAOFactory.getInstance().getCountryDAO();
        Connection connection = JDBCUtil.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM cities WHERE fullname = ?");
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            rs.next();
            city.setId(rs.getLong("city_id"));
            city.setName(name);
            city.setCountry(countryDAO.getCountryById(rs.getLong("country_id")));
        } catch (SQLException ex){
            city = new City();
        } finally {
            if (connection != null){
                try {
                    connection.close();
                } catch (SQLException ex){
                }
            }
        }
        return city;
    }

    public List<City> getCitiesByCountry(Country country) {
        LinkedList<City> cities = new LinkedList<City>();
        Connection connection = JDBCUtil.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM cities WHERE country_id = ?");
            ps.setLong(1, country.getId());
            ResultSet rs = ps.executeQuery();
            City city;
            while (rs.next()){
                city = new City();
                city.setId(rs.getLong("city_id"));
                city.setName(rs.getString("fullname"));
                city.setCountry(country);

                cities.addLast(city);
            }
        } catch (SQLException ex){
            cities = new LinkedList<City>();
        } finally {
            if (connection != null){
                try {
                    connection.close();
                } catch (SQLException ex){
                }
            }
        }
        return cities;
    }

    public List<City> getAllCities() {
        LinkedList<City> cities = new LinkedList<City>();
        CountryDAO countryDAO = DAOFactory.getInstance().getCountryDAO();
        Connection connection = JDBCUtil.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM cities");
            ResultSet rs = ps.executeQuery();
            City city;
            while (rs.next()){
                city = new City();
                city.setId(rs.getLong("city_id"));
                city.setName(rs.getString("fullname"));
                city.setCountry(countryDAO.getCountryById(rs.getLong("country_id")));

                cities.addLast(city);
            }
        } catch (SQLException ex){
            cities = new LinkedList<City>();
        } finally {
            if (connection != null){
                try {
                    connection.close();
                } catch (SQLException ex){
                }
            }
        }
        return cities;
    }
}
