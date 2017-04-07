package models;

import java.io.Serializable;

public class Ration implements Serializable {

    private static final long serialVersionUID = 9L;

    private long id;
    private String description;

    public Ration() {
    }

    public Ration(String description) {
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
        return "Ration[" +
                        "id=" + id +
                        ", description=" + description +
                "]";
    }
}
