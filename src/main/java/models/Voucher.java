package models;

import java.io.Serializable;

public class Voucher implements Serializable {

    private static final long serialVersionUID = 11L;

    private long id;
    private double cost;
    private Route route;
    private AirlaneCompany airlaneCompany;
    private Hotel hotel;
    private City city;

    public Voucher() {
    }

    public Voucher(double cost, Route route, AirlaneCompany airlaneCompany, Hotel hotel, City city) {
        this.cost = cost;
        this.route = route;
        this.airlaneCompany = airlaneCompany;
        this.hotel = hotel;
        this.city = city;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public AirlaneCompany getAirlaneCompany() {
        return airlaneCompany;
    }

    public void setAirlaneCompany(AirlaneCompany airlaneCompany) {
        this.airlaneCompany = airlaneCompany;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Voucher[" +
                "id=" + id +
                ", cost=" + cost +
                ", route=" + route +
                ", airlaneCompany=" + airlaneCompany +
                ", hotel=" + hotel +
                ", city=" + city +
                "]";
    }
}
