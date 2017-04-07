package models;

import java.io.Serializable;

public class Hotel implements Serializable {

    private static final long serialVersionUID = 10L;

    private long id;
    private String name;
    private int starsCount;
    private String location;
    private Ration ration;
    private HotelType hotelType;

    public Hotel() {
    }

    public Hotel(String name, int starsCount, String location, Ration ration, HotelType hotelType) {
        this.name = name;
        this.starsCount = starsCount;
        this.location = location;
        this.ration = ration;
        this.hotelType = hotelType;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStarsCount() {
        return starsCount;
    }

    public void setStarsCount(int starsCount) {
        this.starsCount = starsCount;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Ration getRation() {
        return ration;
    }

    public void setRation(Ration ration) {
        this.ration = ration;
    }

    public HotelType getHotelType() {
        return hotelType;
    }

    public void setHotelType(HotelType hotelType) {
        this.hotelType = hotelType;
    }

    @Override
    public String toString() {
        return "Hotel[" +
                "id=" + id +
                ", name=" + name +
                ", starsCount=" + starsCount +
                ", location=" + location +
                ", ration=" + ration +
                ", hotelType=" + hotelType +
                "]";
    }
}
