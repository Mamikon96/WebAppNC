package models;

import java.io.Serializable;

public class HotelType implements Serializable {

    private static final long serialVersionUID = 8L;

    private long id;
    private String description;

    public HotelType() {
    }

    public HotelType(String description) {
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "HotelType[" +
                            "id=" + id +
                            ", description=" + description +
                "]";
    }
}
