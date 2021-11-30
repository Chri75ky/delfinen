package com.company.domain.Member;

import com.company.domain.Member.Member;

public class CompSwimmer extends Member {
    private String discipline;

    public CompSwimmer(String fullName, int age, boolean activeMembership, String discipline) {
        super(fullName, age, activeMembership);
        this.discipline = discipline;
    }

    @Override
    public String toString() {
        return "CompSwimmer{" +
                "FullName='" + fullName + '\'' +
                ", age=" + age +
                ", activeMembership=" + activeMembership +
                ", team='" + team + '\'' +
                ", discipline='" + discipline + '\'' +
                '}';
    }
}
