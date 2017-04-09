package dao.daoImpl;

import dao.DAOFactory;
import dao.HotelDAO;
import dao.HotelTypeDAO;
import dao.RationDAO;
import models.Hotel;
import util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class HotelDAOImpl implements HotelDAO {
    public boolean addHotel(Hotel hotel) {
        boolean flag = true;
        Connection connection = JDBCUtil.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO hotels" +
                    "(hotel_id, fullname, stars, location, ration_id, type_id) " +
                    "VALUES (hotels_seq.NEXTVAL, ?, ?, ?, ?, ?)");
            ps.setString(1, hotel.getName());
            ps.setInt(2, hotel.getStarsCount());
            ps.setString(3, hotel.getLocation());
            ps.setLong(4, hotel.getRation().getId());
            ps.setLong(5, hotel.getHotelType().getId());
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

    public boolean updateHotel(Hotel hotel) {
        boolean flag = true;
        Connection connection = JDBCUtil.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE hotels SET " +
                                                                            "fullname = ?," +
                                                                            "stars = ?," +
                                                                            "location = ?," +
                                                                            "ration_id = ?," +
                                                                            "type_id = ?) " +
                                                                    "WHERE hotel_id = ?");
            ps.setString(1, hotel.getName());
            ps.setInt(2, hotel.getStarsCount());
            ps.setString(3, hotel.getLocation());
            ps.setLong(4, hotel.getRation().getId());
            ps.setLong(5, hotel.getHotelType().getId());
            ps.setLong(6, hotel.getId());
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

    public boolean deleteHotel(Hotel hotel) {
        boolean flag = true;
        Connection connection = JDBCUtil.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM hotels WHERE hotel_id = ?");
            ps.setLong(1, hotel.getId());
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

    public Hotel getHotelById(long id) {
        Hotel hotel = new Hotel();
        RationDAO rationDAO = DAOFactory.getInstance().getRationDAO();
        HotelTypeDAO hotelTypeDAO = DAOFactory.getInstance().getHotelTypeDAO();
        Connection connection = JDBCUtil.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM hotels WHERE hotel_id = ?");
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            rs.next();
            hotel.setId(id);
            hotel.setName(rs.getString("fullname"));
            hotel.setStarsCount(rs.getInt("stars"));
            hotel.setLocation(rs.getString("location"));
            hotel.setRation(rationDAO.getRationById(rs.getLong("ration_id")));
            hotel.setHotelType(hotelTypeDAO.getHotelTypeById(rs.getLong("type_id")));
        } catch (SQLException ex){
            hotel = new Hotel();
        } finally {
            if (connection != null){
                try {
                    connection.close();
                } catch (SQLException ex){
                }
            }
        }
        return hotel;
    }

    public Hotel getHotelByName(String name) {
        Hotel hotel = new Hotel();
        RationDAO rationDAO = DAOFactory.getInstance().getRationDAO();
        HotelTypeDAO hotelTypeDAO = DAOFactory.getInstance().getHotelTypeDAO();
        Connection connection = JDBCUtil.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM hotels WHERE fullname = ?");
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            rs.next();
            hotel.setId(rs.getLong("hotel_id"));
            hotel.setName(name);
            hotel.setStarsCount(rs.getInt("stars"));
            hotel.setLocation(rs.getString("location"));
            hotel.setRation(rationDAO.getRationById(rs.getLong("ration_id")));
            hotel.setHotelType(hotelTypeDAO.getHotelTypeById(rs.getLong("type_id")));
        } catch (SQLException ex){
            hotel = new Hotel();
        } finally {
            if (connection != null){
                try {
                    connection.close();
                } catch (SQLException ex){
                }
            }
        }
        return hotel;
    }

    public List<Hotel> getHotelsByStarsCount(int minStars, int maxStars) {
        List<Hotel> hotels;
        Connection connection = JDBCUtil.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM hotels " +
                                                                    "WHERE stars BETWEEN ? AND ?");
            ps.setInt(1, minStars);
            ps.setInt(2, maxStars);
            ResultSet rs = ps.executeQuery();
            hotels = getHotels(rs);
        } catch (SQLException ex){
            hotels = new LinkedList<Hotel>();
        } finally {
            if (connection != null){
                try {
                    connection.close();
                } catch (SQLException ex){
                }
            }
        }
        return hotels;
    }

    public List<Hotel> getAllHotels() {
        List<Hotel> hotels;
        Connection connection = JDBCUtil.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM hotels");
            ResultSet rs = ps.executeQuery();
            hotels = getHotels(rs);
        } catch (SQLException ex){
            hotels = new LinkedList<Hotel>();
        } finally {
            if (connection != null){
                try {
                    connection.close();
                } catch (SQLException ex){
                }
            }
        }
        return hotels;
    }

    private List<Hotel> getHotels(ResultSet rs) throws SQLException {
        LinkedList<Hotel> hotels = new LinkedList<Hotel>();
        Hotel hotel;
        RationDAO rationDAO = DAOFactory.getInstance().getRationDAO();
        HotelTypeDAO hotelTypeDAO = DAOFactory.getInstance().getHotelTypeDAO();
        while (rs.next()){
            hotel = new Hotel();
            hotel.setId(rs.getLong("hotel_id"));
            hotel.setName(rs.getString("fullname"));
            hotel.setStarsCount(rs.getInt("stars"));
            hotel.setLocation(rs.getString("location"));
            hotel.setRation(rationDAO.getRationById(rs.getLong("ration_id")));
            hotel.setHotelType(hotelTypeDAO.getHotelTypeById(rs.getLong("type_id")));

            hotels.addLast(hotel);
        }
        return hotels;
    }
}
