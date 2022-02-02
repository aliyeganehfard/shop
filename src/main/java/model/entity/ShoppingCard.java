package model.entity;

import java.sql.Date;

public class ShoppingCard {
    private Integer id;
    private Date date;
    private boolean payed;

    public ShoppingCard() {
    }

    public ShoppingCard(Integer id) {
        this.id = id;
    }

    public ShoppingCard(Date date, boolean payed) {
        this.date = date;
        this.payed = payed;
    }

    public ShoppingCard(Integer id, Date date, boolean payed) {
        this.id = id;
        this.date = date;
        this.payed = payed;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isPayed() {
        return payed;
    }

    public void setPayed(boolean payed) {
        this.payed = payed;
    }

    @Override
    public String toString() {
        return "ShoppingCard{" +
                "id=" + id +
                ", date=" + date +
                ", payed=" + payed +
                '}';
    }
}
