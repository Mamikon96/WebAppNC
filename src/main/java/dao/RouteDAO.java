package dao;

import models.City;
import models.Route;

import java.util.List;

public interface RouteDAO {
    boolean addRoute(Route route);
    boolean updateRoute(Route route);
    boolean deleteRoute(Route route);
    Route getRouteById(long id);
    List<Route> getRoutesByFrom(City city);
    List<Route> getRoutesByTo(City city);
    List<Route> getAllRoutes();
}
