package com.company.domain.Member;

import com.company.domain.Member.Member;
import com.company.domain.User;

public class CompSwimmer extends Member {
    private final String discipline;
    private User coach;

    public CompSwimmer(String fullName, int age, boolean activeMembership, String discipline) {
        super(fullName, age, activeMembership);
        this.discipline = discipline;
        //TODO Tilføj en måde at sætte en træner på svømmeren ud fra deres alder (under 18 vs 18 og over)
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
