package com.company.domain;

public class Member {

    protected String fullName;
    protected int age;
    protected boolean activeMembership;
    protected String team;
    private Kontingent kontingent = new Kontingent();

    public Member(String fullName, int age, boolean activeMembership) {
        this.fullName = fullName;
        this.age = age;
        this.activeMembership = activeMembership;
        if (age < 18) {
            this.team = "Junior";
        } else this.team = "Senior";

        makeKontigent();
    }

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
