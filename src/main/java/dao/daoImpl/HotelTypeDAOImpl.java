package dao.daoImpl;

import dao.HotelTypeDAO;
import models.HotelType;
import util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class HotelTypeDAOImpl implements HotelTypeDAO {
    public boolean addHotelType(HotelType hotelType) {
        boolean flag = true;
        Connection connection = JDBCUtil.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO hotel_types" +
                    "(type_id, description) " +
                    "VALUES (hotel_types_seq.NEXTVAL, ?)");
            ps.setString(1, hotelType.getDescription());
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

    public boolean updateHotelType(HotelType hotelType) {
        boolean flag = true;
        Connection connection = JDBCUtil.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE hotel_types SET " +
                    "description = ?" +
                    "WHERE type_id = ?");
            ps.setString(1, hotelType.getDescription());
            ps.setLong(2, hotelType.getId());
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

    public boolean deleteHotelType(HotelType hotelType) {
        boolean flag = true;
        Connection connection = JDBCUtil.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM hotel_types WHERE type_id = ?");
            ps.setLong(1, hotelType.getId());
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

    public HotelType getHotelTypeById(long id) {
        HotelType hotelType = new HotelType();
        Connection connection = JDBCUtil.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM hotel_types WHERE type_id = ?");
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            rs.next();
            hotelType.setId(id);
            hotelType.setDescription(rs.getString("description"));
        } catch (SQLException ex){
            hotelType = new HotelType();
        } finally {
            if (connection != null){
                try {
                    connection.close();
                } catch (SQLException ex){
                }
            }
        }
        return hotelType;
    }

    public HotelType getHotelTypeByDescription(String description) {
        HotelType hotelType = new HotelType();
        Connection connection = JDBCUtil.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM hotel_types WHERE description = ?");
            ps.setString(1, description);
            ResultSet rs = ps.executeQuery();
            rs.next();
            hotelType.setId(rs.getLong("type_id"));
            hotelType.setDescription(description);
        } catch (SQLException ex){
            hotelType = new HotelType();
        } finally {
            if (connection != null){
                try {
                    connection.close();
                } catch (SQLException ex){
                }
            }
        }
        return hotelType;
    }

    public List<HotelType> getAllHotelTypes() {
        LinkedList<HotelType> hotelTypes = new LinkedList<HotelType>();
        Connection connection = JDBCUtil.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM hotel_types");
            ResultSet rs = ps.executeQuery();
            HotelType hotelType;
            while (rs.next()) {
                hotelType = new HotelType();
                hotelType.setId(rs.getLong("type_id"));
                hotelType.setDescription(rs.getString("description"));

                hotelTypes.addLast(hotelType);
            }
        } catch (SQLException ex){
            hotelTypes = new LinkedList<HotelType>();
        } finally {
            if (connection != null){
                try {
                    connection.close();
                } catch (SQLException ex){
                }
            }
        }
        return hotelTypes;
    }
}
