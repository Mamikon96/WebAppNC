package models;

import java.io.Serializable;

public class City implements Serializable {

    private static final long serialVersionUID = 6L;

    private long id;
    private String name;
    private Country country;

    public City() {
    }

    public City(String name, Country country) {
        this.name = name;
        this.country = country;
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

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "City[" +
                        "id=" + id +
                        ", name=" + name +
                        ", country=" + country +
                "]";
    }
}
