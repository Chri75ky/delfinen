package com.company.domain;

public class CompSwimmer extends Member{
    private String discipline;

    public CompSwimmer(String fullName, int age, String membership, String team, String discipline) {
        super(fullName, age, membership, team);
        this.discipline = discipline;
    }
}
