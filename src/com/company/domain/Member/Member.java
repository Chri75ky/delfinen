package com.company.domain.Member;


public class Member {

    protected String fullName;
    protected int age;
    protected boolean activeMembership;
    protected String team;

    //konstruktør
    public Member(String fullName, int age, boolean activeMembership) {
        this.fullName = fullName;
        this.age = age;
        this.activeMembership = activeMembership;
        if (age < 18) {
            this.team = "Junior";
        } else this.team = "Senior";
    }

    public String toCSV() {
        return fullName + ";" +
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

    //toString
    @Override
    public String toString() {
        return "Medlem: " + fullName + "\n" + "Alder: " + age + "\n" + "Aktivt medlemskab: " + activeMembership + "\n" + "Hold: " + team;
    }
}
