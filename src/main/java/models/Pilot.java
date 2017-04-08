package models;

import java.io.Serializable;

public class Pilot implements Serializable {

    private static final long serialVersionUID = 1L;

    private long id;
    private String fullname;
    private int expirience;

    public Pilot() {
    }

    public Pilot(String fullname, int expirience) {
        this.fullname = fullname;
        this.expirience = expirience;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public int getExpirience() {
        return expirience;
    }

    public void setExpirience(int expirience) {
        this.expirience = expirience;
    }

    @Override
    public String toString() {
        return "Pilot[" +
                        "id=" + id +
                        ", fullname=" + fullname +
                        ", expirience=" + expirience +
                "]";
    }
}
