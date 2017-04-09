package models;

import java.io.Serializable;

public class Plane implements Serializable {

    private static final long serialVersionUID = 2L;

    private long id;
    private String name;
    private int places;
    private Pilot pilot;

    public Plane() {
    }

    public Plane(String name, int places, Pilot pilot) {
        this.name = name;
        this.places = places;
        this.pilot = pilot;
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
                        ", name=" + name +
                        ", places=" + places +
                        ", pilot=" + pilot +
                "]";
    }
}
