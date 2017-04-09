package dao.daoImpl;

import dao.DAOFactory;
import dao.PilotDAO;
import dao.PlaneDAO;
import models.Plane;
import util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class PlaneDAOImpl implements PlaneDAO {
    public boolean addPlane(Plane plane) {
        boolean flag = true;
        Connection connection = JDBCUtil.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO planes" +
                                                                    "(plane_id, name, places, pilot_id) " +
                                                                    "VALUES (planes_seq.NEXTVAL, ?, ?, ?)");
            ps.setString(1, plane.getName());
            ps.setInt(2, plane.getPlaces());
            ps.setLong(3, plane.getPilot().getId());
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

    public boolean updatePlane(Plane plane) {
        boolean flag = true;
        Connection connection = JDBCUtil.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE planes SET " +
                                                                            "name = ?" +
                                                                            "places = ?," +
                                                                            "pilot_id = ?" +
                                                                    "WHERE plane_id = ?");
            ps.setString(1, plane.getName());
            ps.setInt(2, plane.getPlaces());
            ps.setLong(3, plane.getPilot().getId());
            ps.setLong(4, plane.getId());
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

    public boolean deletePlane(Plane plane) {
        boolean flag = true;
        Connection connection = JDBCUtil.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM planes WHERE plane_id = ?");
            ps.setLong(1, plane.getId());
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

    public Plane getPlaneById(long id) {
        Plane plane = new Plane();
        PilotDAO pilotDAO = DAOFactory.getInstance().getPilotDAO();
        Connection connection = JDBCUtil.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM planes WHERE plane_id = ?");
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            rs.next();
            plane.setId(id);
            plane.setName(rs.getString("name"));
            plane.setPlaces(rs.getInt("places"));
            plane.setPilot(pilotDAO.getPilotById(rs.getLong("pilot_id")));
        } catch (SQLException ex){
            plane = new Plane();
        } finally {
            if (connection != null){
                try {
                    connection.close();
                } catch (SQLException ex){
                }
            }
        }
        return plane;
    }

    public Plane getPlaneByName(String name) {
        Plane plane = new Plane();
        PilotDAO pilotDAO = DAOFactory.getInstance().getPilotDAO();
        Connection connection = JDBCUtil.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM planes WHERE name = ?");
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            rs.next();
            plane.setId(rs.getLong("plane_id"));
            plane.setName(name);
            plane.setPlaces(rs.getInt("places"));
            plane.setPilot(pilotDAO.getPilotById(rs.getLong("pilot_id")));
        } catch (SQLException ex){
            plane = new Plane();
        } finally {
            if (connection != null){
                try {
                    connection.close();
                } catch (SQLException ex){
                }
            }
        }
        return plane;
    }

    public List<Plane> getPlanesByPlaces(int minPlaces, int maxPlaces) {
        List<Plane> planes;
        Connection connection = JDBCUtil.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * " +
                                                                    "FROM planes " +
                                                                    "WHERE places BETWEEN ? AND ?");
            ps.setInt(1, minPlaces);
            ps.setInt(2, maxPlaces);
            ResultSet rs = ps.executeQuery();

            planes = getPlanes(rs);
        } catch (SQLException ex){
            planes = new ArrayList<Plane>();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex){
                }
            }
        }
        return planes;
    }

    public List<Plane> getAllPlanes() {
        List<Plane> planes;
        Connection connection = JDBCUtil.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM planes ");
            ResultSet rs = ps.executeQuery();
            planes = getPlanes(rs);
        } catch (SQLException ex){
            planes = new ArrayList<Plane>();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex){
                }
            }
        }
        return planes;
    }

    private List<Plane> getPlanes(ResultSet rs) throws SQLException {
        LinkedList<Plane> planes = new LinkedList<Plane>();
        Plane plane;
        PilotDAO pilotDAO = DAOFactory.getInstance().getPilotDAO();
        while (rs.next()){
            plane = new Plane();
            plane.setId(rs.getLong("plane_id"));
            plane.setName(rs.getString("name"));
            plane.setPlaces(rs.getInt("places"));
            plane.setPilot(pilotDAO.getPilotById(rs.getLong("pilot_id")));

            planes.addLast(plane);
        }
        return planes;
    }
}
