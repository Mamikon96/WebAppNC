package models;

import java.io.Serializable;

public class Plane implements Serializable {

    private static final long serialVersionUID = 2L;

    private long id;
    private int places;
    private Pilot pilot;

    public Plane() {
    }

    public Plane(Integer places, Pilot pilot) {
        this.places = places;
        this.pilot = pilot;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getPlaces() {
        return places;
    }

    public void setPlaces(int places) {
        this.places = places;
    }

    public Pilot getPilot() {
        return pilot;
    }

    public void setPilot(Pilot pilot) {
        this.pilot = pilot;
    }

    @Override
    public String toString() {
        return "Plane[" +
                        "id=" + id +
                        ", places=" + places +
                        ", pilot=" + pilot +
                "]";
    }
}
