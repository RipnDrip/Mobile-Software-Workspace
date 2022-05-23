package edu.uga.cs.frugalshopper;

public class Item {
    private double price;
    private int pounds;
    private int ounces;

    public Item() {
        this.price = 0;
        this.pounds = 0;
        this.ounces = 0;
    }

    public Item(double price, int pounds, int ounces) {
        this.price = price;
        this.pounds = pounds;
        this.ounces = ounces;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getPounds() {
        return pounds;
    }

    public void setPounds(int pounds) {
        this.pounds = pounds;
    }

    public int getOunces() {
        return ounces;
    }

    public void setOunces(int ounces) {
        this.ounces = ounces;
    }
}
