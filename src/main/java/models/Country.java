package models;

import java.io.Serializable;

public class Country implements Serializable {

    private static final long serialVersionUID = 5L;

    private long id;
    private String name;

    public Country() {
    }

    public Country(String name) {
        this.name = name;
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

    @Override
    public String toString() {
        return "Country[" +
                        "id=" + id +
                        ", name=" + name +
                "]";
    }
}
