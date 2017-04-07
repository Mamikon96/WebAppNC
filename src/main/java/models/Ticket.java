package models;

import java.io.Serializable;

public class Ticket implements Serializable {

    private static final long serialVersionUID = 13L;

    private long id;
    private String buyer;
    private String data;
    private Vaucher vaucher;

    public Ticket() {
    }

    public Ticket(String buyer, String data, Vaucher vaucher) {
        this.buyer = buyer;
        this.data = data;
        this.vaucher = vaucher;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Vaucher getVaucher() {
        return vaucher;
    }

    public void setVaucher(Vaucher vaucher) {
        this.vaucher = vaucher;
    }

    @Override
    public String toString() {
        return "Ticket[" +
                "id=" + id +
                ", buyer=" + buyer +
                ", data=" + data +
                ", vaucher=" + vaucher +
                "]";
    }
}
