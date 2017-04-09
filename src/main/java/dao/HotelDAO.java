package dao;

import models.Hotel;

import java.util.List;

public interface HotelDAO {
    boolean addHotel(Hotel hotel);
    boolean updateHotel(Hotel hotel);
    boolean deleteHotel(Hotel hotel);
    Hotel getHotelById(long id);
    Hotel getHotelByName(String name);
    List<Hotel> getHotelsByStarsCount(int minStars, int maxStars);
    List<Hotel> getAllHotels();
}
