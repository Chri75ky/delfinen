package com.company.domain.Member;

import com.company.domain.User.User;

public class CompSwimmer extends Member {
    private final Disciplin discipline;
    private User coach;

    public CompSwimmer(String fullName, int age, boolean activeMembership, Disciplin discipline) {
        super(fullName, age, activeMembership);
        this.discipline = discipline;
        //TODO Tilføj en måde at sætte en træner på svømmeren ud fra deres alder (under 18 vs 18 og over)
    }

    public String toCSV() {
        return fullName + ";" +
                age + ";" +
                activeMembership + ";" +
                team + ";" +
                discipline;
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
