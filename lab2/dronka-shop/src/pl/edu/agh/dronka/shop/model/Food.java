package pl.edu.agh.dronka.shop.model;

import java.util.Date;

public class Food extends Item {

    private Date expirationDate;

    public Food(String name, Category category, int price, int quantity) {
        super(name, category, price, quantity);
    }

    public Food() {
    }

    public void setExpirationDate(Date expirationDate) { this.expirationDate = expirationDate; }

    public Date getExpirationDate() { return this.expirationDate; }
}
