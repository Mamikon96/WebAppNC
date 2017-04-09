package dao.daoImpl;

import dao.RationDAO;
import models.Ration;
import util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class RationDAOImpl implements RationDAO {
    public boolean addRation(Ration ration) {
        boolean flag = true;
        Connection connection = JDBCUtil.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO rations " +
                            "(ration_id, description) " +
                    "VALUES (rations_seq.NEXTVAL, ?)");
            ps.setString(1, ration.getDescription());
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

    public boolean updateRation(Ration ration) {
        boolean flag = true;
        Connection connection = JDBCUtil.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("UPADTE rations SET " +
                    "description = ?" +
                    "WHERE ration_id = ?");
            ps.setString(1, ration.getDescription());
            ps.setLong(2, ration.getId());
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

    public boolean deleteRation(Ration ration) {
        boolean flag = true;
        Connection connection = JDBCUtil.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM rations WHERE ration_id = ?");
            ps.setLong(1, ration.getId());
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

    public Ration getRationById(long id) {
        Ration ration = new Ration();
        Connection connection = JDBCUtil.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * " +
                    "FROM rations " +
                    "WHERE ration_id = ?");
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            rs.next();
            ration.setId(id);
            ration.setDescription(rs.getString("description"));
        } catch (SQLException ex){
            ration = new Ration();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex){
                }
            }
        }
        return ration;
    }

    public Ration getRationByDescription(String description) {
        Ration ration = new Ration();
        Connection connection = JDBCUtil.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * " +
                    "FROM rations " +
                    "WHERE description = ?");
            ps.setString(1, description);
            ResultSet rs = ps.executeQuery();
            rs.next();
            ration.setId(rs.getLong("ration_id"));
            ration.setDescription(description);
        } catch (SQLException ex){
            ration = new Ration();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex){
                }
            }
        }
        return ration;
    }

    public List<Ration> getAllRations() {
        LinkedList<Ration> rations = new LinkedList<Ration>();
        Connection connection = JDBCUtil.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM rations");
            ResultSet rs = ps.executeQuery();

            Ration ration;
            while (rs.next()){
                ration = new Ration();
                ration.setId(rs.getLong("ration_id"));
                ration.setDescription(rs.getString("description"));

                rations.addLast(ration);
            }
        } catch (SQLException ex){
            rations = new LinkedList<Ration>();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex){
                }
            }
        }
        return rations;
    }
}
