package com.company.domain;

public class Member {

    protected String fullName;
    protected int age;
    protected String membership;
    protected String team;

    public Member(String fullName, int age, String membership, String team) {
        this.fullName = fullName;
        this.age = age;
        this.membership = membership;
        this.team = team;
    }
}
