package dao.daoImpl;

import dao.*;
import models.City;
import models.Route;
import util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class RouteDAOImpl implements RouteDAO {
    public boolean addRoute(Route route) {
        boolean flag = true;
        Connection connection = JDBCUtil.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO routes" +
                    "(route_id, from_city_id, to_city_id, flight_time, plane_id) " +
                    "VALUES (routes_seq.NEXTVAL, ?, ?, ?, ?)");
            ps.setLong(1, route.getStartCity().getId());
            ps.setLong(2, route.getEndCity().getId());
            ps.setString(3, route.getFlightTime());
            ps.setLong(4, route.getPlane().getId());
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

    public boolean updateRoute(Route route) {
        boolean flag = true;
        Connection connection = JDBCUtil.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE routes SET" +
                                                                            "from_city_id = ?," +
                                                                            "to_city_id = ?," +
                                                                            "flight_time = ?," +
                                                                            "plane_id = ?" +
                                                                    "WHERE route_id = ?");
            ps.setLong(1, route.getStartCity().getId());
            ps.setLong(2, route.getEndCity().getId());
            ps.setString(3, route.getFlightTime());
            ps.setLong(4, route.getPlane().getId());
            ps.setLong(5, route.getId());
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

    public boolean deleteRoute(Route route) {
        boolean flag = true;
        Connection connection = JDBCUtil.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM routes WHERE route_id = ?");
            ps.setLong(1, route.getId());
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

    public Route getRouteById(long id) {
        Route route = new Route();
        CityDAO cityDAO = DAOFactory.getInstance().getCityDAO();
        PlaneDAO planeDAO = DAOFactory.getInstance().getPlaneDAO();
        Connection connection = JDBCUtil.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM routes WHERE route_id = ?");
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            rs.next();
            route.setId(id);
            route.setStartCity(cityDAO.getCityById(rs.getLong("from_city_id")));
            route.setEndCity(cityDAO.getCityById(rs.getLong("to_city_id")));
            route.setFlightTime(rs.getString("flight_time"));
            route.setPlane(planeDAO.getPlaneById(rs.getLong("plane_id")));
        } catch (SQLException ex){
            route = new Route();
        } finally {
            if (connection != null){
                try {
                    connection.close();
                } catch (SQLException ex){
                }
            }
        }
        return route;
    }

    public List<Route> getRoutesByFrom(City city) {
        List<Route> routes;
        Connection connection = JDBCUtil.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM routes " +
                                                                    "WHERE from_city_id = ?");
            ps.setLong(1, city.getId());
            ResultSet rs = ps.executeQuery();
            routes = getRoutes(rs);
        } catch (SQLException ex){
            routes = new LinkedList<Route>();
        } finally {
            if (connection != null){
                try {
                    connection.close();
                } catch (SQLException ex){
                }
            }
        }
        return routes;
    }

    public List<Route> getRoutesByTo(City city) {
        List<Route> routes;
        Connection connection = JDBCUtil.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM routes " +
                                                                    "WHERE to_city_id = ?");
            ps.setLong(1, city.getId());
            ResultSet rs = ps.executeQuery();
            routes = getRoutes(rs);
        } catch (SQLException ex){
            routes = new LinkedList<Route>();
        } finally {
            if (connection != null){
                try {
                    connection.close();
                } catch (SQLException ex){
                }
            }
        }
        return routes;
    }

    public List<Route> getAllRoutes() {
        List<Route> routes;
        Connection connection = JDBCUtil.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM routes");
            ResultSet rs = ps.executeQuery();
            routes = getRoutes(rs);
        } catch (SQLException ex){
            routes = new LinkedList<Route>();
        } finally {
            if (connection != null){
                try {
                    connection.close();
                } catch (SQLException ex){
                }
            }
        }
        return routes;
    }

    private List<Route> getRoutes(ResultSet rs) throws SQLException {
        LinkedList<Route> routes = new LinkedList<Route>();
        Route route;
        CityDAO cityDAO = DAOFactory.getInstance().getCityDAO();
        PlaneDAO planeDAO = DAOFactory.getInstance().getPlaneDAO();
        while (rs.next()){
            route = new Route();
            route.setId(rs.getLong("route_id"));
            route.setStartCity(cityDAO.getCityById(rs.getInt("from_city_id")));
            route.setEndCity(cityDAO.getCityById(rs.getInt("to_city_id")));
            route.setFlightTime(rs.getString("flight_time"));
            route.setPlane(planeDAO.getPlaneById(rs.getLong("plane_id")));

            routes.addLast(route);
        }
        return routes;
    }
}
