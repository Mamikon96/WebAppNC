package dao.daoImpl;

import dao.CountryDAO;
import models.Country;
import util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CountryDAOImpl implements CountryDAO {
    public boolean addCountry(Country country) {
        boolean flag = true;
        Connection connection = JDBCUtil.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO countries" +
                                                                    "(country_id, fullname) " +
                                                                    "VALUES (countries_seq.NEXTVAL, ?)");
            ps.setString(1, country.getName());
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

    public boolean updateCountry(Country country) {
        boolean flag = true;
        Connection connection = JDBCUtil.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE countries SET " +
                                                                    "fullname = ? " +
                                                                    "WHERE country_id = ?");
            ps.setString(1, country.getName());
            ps.setLong(2, country.getId());
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

    public boolean deleteCountry(Country country) {
        boolean flag = true;
        Connection connection = JDBCUtil.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM countries WHERE country_id = ?");
            ps.setLong(1, country.getId());
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

    public Country getCountryById(long id) {
        Country country = new Country();
        Connection connection = JDBCUtil.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM countries WHERE country_id = ?");
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            rs.next();
            country.setId(id);
            country.setName(rs.getString("fullname"));
        } catch (SQLException ex){
            country = new Country();
        } finally {
            if (connection != null){
                try {
                    connection.close();
                } catch (SQLException ex){
                }
            }
        }
        return country;
    }

    public Country getCountryByName(String name) {
        Country country = new Country();
        Connection connection = JDBCUtil.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM countries WHERE fullname = ?");
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            rs.next();
            country.setId(rs.getLong("country_id"));
            country.setName(name);
        } catch (SQLException ex){
            country = new Country();
        } finally {
            if (connection != null){
                try {
                    connection.close();
                } catch (SQLException ex){
                }
            }
        }
        return country;
    }

    public List<Country> getAllCountries() {
        LinkedList<Country> countries = new LinkedList<Country>();
        Connection connection = JDBCUtil.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM countries");
            ResultSet rs = ps.executeQuery();
            Country country;
            while (rs.next()){
                country = new Country();
                country.setId(rs.getLong("country_id"));
                country.setName(rs.getString("fullname"));

                countries.addLast(country);
            }
        } catch (SQLException ex){
            countries = new LinkedList<Country>();
        } finally {
            if (connection != null){
                try {
                    connection.close();
                } catch (SQLException ex){
                }
            }
        }
        return countries;
    }
}
