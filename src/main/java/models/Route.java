package models;

import java.io.Serializable;

public class Route implements Serializable {

    private static final long serialVersionUID = 4L;

    private long id;
    private City startCity;
    private City endCity;
    private String flightTime;
    private Plane plane;

    public Route() {
    }

    public Route(City startCity, City endCity, String flightTime, Plane plane) {
        this.startCity = startCity;
        this.endCity = endCity;
        this.flightTime = flightTime;
        this.plane = plane;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public City getStartCity() {
        return startCity;
    }

    public void setStartCity(City startCity) {
        this.startCity = startCity;
    }

    public City getEndCity() {
        return endCity;
    }

    public void setEndCity(City endCity) {
        this.endCity = endCity;
    }

    public String getFlightTime() {
        return flightTime;
    }

    public void setFlightTime(String flightTime) {
        this.flightTime = flightTime;
    }

    public Plane getPlane() {
        return plane;
    }

    public void setPlane(Plane plane) {
        this.plane = plane;
    }

    @Override
    public String toString() {
        return "Route[" +
                        "id=" + id +
                        ", startCity=" + startCity +
                        ", endCity=" + endCity +
                        ", flightTime=" + flightTime +
                        ", plane=" + plane +
                "]";
    }
}
