package models;

import java.io.Serializable;

public class Tour implements Serializable {

    private static final long serialVersionUID = 12L;

    private long id;
    private Vaucher vaucher;

    public Tour() {
    }

    public Tour(Vaucher vaucher) {
        this.vaucher = vaucher;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Vaucher getVaucher() {
        return vaucher;
    }

    public void setVaucher(Vaucher vaucher) {
        this.vaucher = vaucher;
    }

    @Override
    public String toString() {
        return "Tour[" +
                "id=" + id +
                ", vaucher=" + vaucher +
                "]";
    }
}
