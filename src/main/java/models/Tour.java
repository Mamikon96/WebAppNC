package models;

import java.io.Serializable;

public class Tour implements Serializable {

    private static final long serialVersionUID = 12L;

    private long id;
    private Voucher voucher;

    public Tour() {
    }

    public Tour(Voucher voucher) {
        this.voucher = voucher;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Voucher getVoucher() {
        return voucher;
    }

    public void setVoucher(Voucher voucher) {
        this.voucher = voucher;
    }

    @Override
    public String toString() {
        return "Tour[" +
                "id=" + id +
                ", voucher=" + voucher +
                "]";
    }
}
