package dao;

import models.Tour;

import java.util.List;

public interface TourDAO {
    boolean addTour(Tour tour);
    boolean updateTour(Tour tour);
    boolean deleteTour(Tour tour);
    Tour getTourById(long id);
    List<Tour> getAllTours();
}
