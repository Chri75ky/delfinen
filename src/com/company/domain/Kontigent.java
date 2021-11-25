package com.company.domain;

public class Kontigent {
    //'Price' used for instant payment, otherwise turn to restance if not paid.
    private int price;
    private int restance = 0;

    public Kontigent() {

    }

    public void setKontigent (int age, boolean membershipStatus) {
        if (membershipStatus == false) {
            // Passivt medlemsskab
            setPrice(500);
        } else {
            if (age >= 18) {
                // Aktivt medlemsskab over/eller 18 år
                if (age > 60) {
                    int discount = 25;
                    setPrice(1600, discount);
                } else {
                    setPrice(1600);
                }

            } else {
                // Aktivt medlemsskab under 18
                setPrice(1000);
            }
        }
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setPrice(int price, int discount) {
        this.price = price - ((price/100)*discount);
    }

    public int getPrice() {
        return this.price;
    }

    public int getRestance() {
        return restance;
    }

    public void changePriceToRestance() {
        this.restance = this.price;
        this.price = 0;
    }

    public void restancePaid(int payment) {
        this.restance -= payment;
    }

    public void pricePaid(int payment) {
        this.price -= payment;
    }


    public String toString() {
        return "Price " + price + " ... Restance " + restance;
    }




}
