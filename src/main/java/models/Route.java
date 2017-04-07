package models;

import java.io.Serializable;

public class Route implements Serializable {

    private static final long serialVersionUID = 4L;

    private long id;
    private String from;
    private String to;
    private String flightTime;
    private Plane plane;

    public Route() {
    }

    public Route(String from, String to, String flightTime, Plane plane) {
        this.from = from;
        this.to = to;
        this.flightTime = flightTime;
        this.plane = plane;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
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
                        ", from=" + from +
                        ", to=" + to +
                        ", flightTime=" + flightTime +
                        ", plane=" + plane +
                "]";
    }
}
