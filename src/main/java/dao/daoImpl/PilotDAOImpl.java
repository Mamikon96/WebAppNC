package dao.daoImpl;

import dao.PilotDAO;
import models.Pilot;
import util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PilotDAOImpl implements PilotDAO {
    public boolean addPilot(Pilot pilot) {
        boolean flag = true;
        Connection connection = JDBCUtil.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO pilots " +
                                                                    "VALUES (pilots_seq.NEXTVAL, ?, ?)");
            ps.setString(1, pilot.getFullname());
            ps.setInt(2, pilot.getExpirience());
            ps.executeUpdate();
        } catch (SQLException ex){
            flag = false;
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex){
                    flag = false;
                }
            } else flag = false;
        }
        return flag;
    }

    public boolean updatePilot(Pilot pilot) {
        boolean flag = true;
        Connection connection = JDBCUtil.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE pilots SET " +
                                                                            "fullname = ?," +
                                                                            "expirience = ?" +
                                                                    "WHERE pilot_id = ?");
            ps.setString(1, pilot.getFullname());
            ps.setInt(2, pilot.getExpirience());
            ps.setLong(3, pilot.getId());
            ps.executeUpdate();
        } catch (SQLException ex){
            flag = false;
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex){
                    flag = false;
                }
            } else flag = false;
        }
        return flag;
    }

    public boolean deletePilot(Pilot pilot) {
        boolean flag = true;
        Connection connection = JDBCUtil.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM pilots " +
                                                                    "WHERE pilot_id = ?");
            ps.setLong(1, pilot.getId());
            ps.executeUpdate();
        } catch (SQLException ex){
            flag = false;
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex){
                    flag = false;
                }
            } else flag = false;
        }
        return flag;
    }

    public Pilot getPilotById(long pilotId) {
        Pilot pilot = new Pilot();
        Connection connection = JDBCUtil.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * " +
                                                                    "FROM pilots " +
                                                                    "WHERE pilot_id = ?");
            ps.setLong(1, pilotId);
            ResultSet rs = ps.executeQuery();
            rs.next();
            pilot.setId(rs.getLong("company_id"));
            pilot.setFullname(rs.getString("fullname"));
            pilot.setExpirience(rs.getInt("expirience"));
        } catch (SQLException ex){
            pilot = new Pilot();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex){
                }
            }
        }
        return pilot;
    }

    public Pilot getPilotByFullname(String fullname) {
        Pilot pilot = new Pilot();
        Connection connection = JDBCUtil.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * " +
                                                                    "FROM pilots " +
                                                                    "WHERE fullname = ?");
            ps.setString(1, fullname);
            ResultSet rs = ps.executeQuery();
            rs.next();
            pilot.setId(rs.getLong("company_id"));
            pilot.setFullname(rs.getString("fullname"));
            pilot.setExpirience(rs.getInt("expirience"));
        } catch (SQLException ex){
            pilot = new Pilot();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex){
                }
            }
        }
        return pilot;
    }

    public List<Pilot> getAllPilots() {
        List<Pilot> pilots = new ArrayList<Pilot>();
        Connection connection = JDBCUtil.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM pilots");
            ResultSet rs = ps.executeQuery();

            Pilot pilot = new Pilot();
            while (rs.next()){
                pilot.setId(rs.getLong("pilot_id"));
                pilot.setFullname(rs.getString("fullname"));
                pilot.setExpirience(rs.getInt("expirience"));

                pilots.add(pilot);
            }
        } catch (SQLException ex){
            pilots = new ArrayList<Pilot>();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex){
                }
            }
        }
        return pilots;
    }
}
