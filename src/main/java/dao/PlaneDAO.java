package dao;

import models.Plane;

import java.util.List;

public interface PlaneDAO {
    boolean addPlane(Plane plane);
    boolean updatePlane(Plane plane);
    boolean deletePlane(Plane plane);
    Plane getPlaneById(long id);
    Plane getPlaneByName(String name);
    List<Plane> getPlanesByPlaces(int minPlaces, int maxPlaces);
    List<Plane> getAllPlanes();
}
