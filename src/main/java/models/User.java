package models;

import java.io.Serializable;

public class User implements Serializable {

    private static final long serialVersionUID = 7L;

    private long id;
    private String login;
    private String password;
    private String data;

    public User() {
    }

    public User(String login, String password, String data) {
        this.login = login;
        this.password = password;
        this.data = data;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "User[" +
                "id=" + id +
                ", login=" + login +
                ", password=" + password +
                ", data=" + data +
                "]";
    }
}
