package dao;

import models.HotelType;

import java.util.List;

public interface HotelTypeDAO {
    boolean addHotelType(HotelType hotelType);
    boolean updateHotelType(HotelType hotelType);
    boolean deleteHotelType(HotelType hotelType);
    HotelType getHotelTypeById(long id);
    HotelType getHotelTypeByDescription(String description);
    List<HotelType> getAllHotelTypes();
}
