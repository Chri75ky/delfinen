package com.company.accounting;

public class Kontingent {
    private final String memberName;
    private final int memberAge;
    private final boolean membershipStatus;
    private double price;
    private boolean isPaid;
    private boolean inRestance;

    public Kontingent(String memberName, int memberAge, boolean membershipStatus, double price, boolean isPaid, boolean inRestance) {
        this.memberName = memberName;
        this.memberAge = memberAge;
        this.membershipStatus = membershipStatus;
        this.price = price;
        this.isPaid = isPaid;
        this.inRestance = inRestance;
    }

    public Kontingent(String memberName, int memberAge, boolean membershipStatus) {
        this.memberName = memberName;
        this.memberAge = memberAge;
        this.membershipStatus = membershipStatus;
    }

    public void setKontingentPrice() {
        if (!this.membershipStatus) {
            // Passivt medlemskab
            setPrice(500);
        } else {
            if (this.memberAge >= 18) {
                // Aktivt medlemskab over/eller 18 år
                if (this.memberAge > 60) {
                    double discount = 25;
                    setPrice(1600, discount);
                } else {
                    setPrice(1600);
                }

            } else {
                // Aktivt medlemskab under 18
                setPrice(1000);
            }
        }
    }

    private void setPrice(double price) {
        this.price = price;
    }

    private void setPrice(double price, double discount) {
        this.price = price - ((price / 100) * discount);
    }

    public double getPrice() {
        return this.price;
    }

    public String getMemberName() {
        return this.memberName;
    }

    public boolean getIsPaid() {
        return isPaid;
    }

    public boolean getInRestance() {
        return this.inRestance;
    }

    public void setInRestance() {
        this.inRestance = true;
    }

    public void isPaid() {
        this.isPaid = true;
    }

    public boolean eligibleForRestance() {
        boolean eligible = false;

        if (!this.inRestance && !this.isPaid) {
            eligible = true;
        }

        return eligible;
    }

    public String toCSV() {

        String membershipStatusText;
        if (this.membershipStatus) {
            membershipStatusText = "Aktivt medlemskab";
        } else {
            membershipStatusText = "Passivt medlemskab";
        }

        String isPaidText;
        if (this.isPaid) {
            isPaidText = "Betalt";
        } else {
            isPaidText = "Ikke betalt";
        }

        String inRestanceText;
        if (this.inRestance) {
            inRestanceText = "Restance";
        } else {
            inRestanceText = "Kontingent";
        }

        return memberName + ";" +
                memberAge + ";" +
                membershipStatusText + ";" +
                price + ";" +
                isPaidText + ";" +
                inRestanceText + "\n";

    }
}
