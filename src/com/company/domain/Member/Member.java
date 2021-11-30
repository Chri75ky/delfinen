package com.company.domain.Member;

import com.company.domain.Kontingent;


public class Member {

    protected String fullName;
    protected int age;
    protected boolean activeMembership;
    protected String team;
    private Kontingent kontingent = new Kontingent();

    //konstruktør
    public Member(String fullName, int age, boolean activeMembership) {
        this.fullName = fullName;
        this.age = age;
        this.activeMembership = activeMembership;
        if (age < 18) {
            this.team = "Junior";
        } else this.team = "Senior";

        makeKontigent();
    }

    //CSV konstruktør
    public Member(String CSV) {
        String[] elements = CSV.split(";");

        this.fullName = elements[0];
        this.age = Integer.parseInt(elements[1]);
        this.activeMembership = Boolean.parseBoolean(elements[2]);
        this.team = elements[3];
    }

    public String toCSV() {
        return  fullName + ";" +
                age + ";" +
                activeMembership + ";" +
                team;
    }

    //Getters
    public String getFullName() {
        return fullName;
    }

    public int getAge() {
        return age;
    }

    public boolean getMembershipStatus() {
        return activeMembership;
    }

    public String getTeam() {
        return team;
    }

    public Kontingent getKontigent() {
        return this.kontingent;
    }

    public void makeKontigent() {
        this.kontingent.setKontigent(this.age, this.activeMembership);
    }

    @Override
    public String toString() {
        return "Medlem: " + fullName + "\n" + "Alder: " + age + "\n" + "Aktivt medlemskab: " + activeMembership + "\n" + "Hold: " + team;
    }
}
