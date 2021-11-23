package com.company.domain;

public class Member {

    protected String fullName;
    protected int age;
    protected boolean activeMembership;
    protected String team;

    public Member(String fullName, int age, boolean activeMembership) {
        this.fullName = fullName;
        this.age = age;
        this.activeMembership = activeMembership;
        if (age < 18) {
            this.team = "Junior";
        } else this.team = "Senior";
    }

    public String getFullName() {
        return fullName;
    }

    public int getAge() {
        return age;
    }

    public boolean isActiveMembership() {
        return activeMembership;
    }

    public String getTeam() {
        return team;
    }

    @Override
    public String toString() {
        return "Member{" +
                "fullName='" + fullName + '\'' +
                ", age=" + age +
                ", activeMembership=" + activeMembership +
                ", team='" + team + '\'' +
                '}';
    }
}
