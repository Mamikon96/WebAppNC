package models;

import java.io.Serializable;

public class AirlaneCompany implements Serializable {

    private static final long serialVersionUID = 3L;

    private long id;
    private String name;
    private double costCoeff;
    private int rating;

    public AirlaneCompany() {
    }

    public AirlaneCompany(String name, double costCoeff, int rating) {
        this.name = name;
        this.costCoeff = costCoeff;
        this.rating = rating;
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

    public double getCostCoeff() {
        return costCoeff;
    }

    public void setCostCoeff(double costCoeff) {
        this.costCoeff = costCoeff;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "AirlaneCompany[" +
                                "id=" + id +
                                ", name=" + name +
                                ", costCoeff=" + costCoeff +
                                ", rating=" + rating +
                "]";
    }
}
