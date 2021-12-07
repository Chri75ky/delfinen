package com.company.domain;

import com.company.domain.Member.CompSwimmer;

public class Competition {
    private String compName;
    private String compDate;
    private CompSwimmer participant;
    private double swimTime;

    public Competition(String compName, String compDate, CompSwimmer participant, double swimTime) {
        this.compName = compName;
        this.compDate = compDate;
        this.participant = participant;
        this.swimTime = swimTime;
    }

    @Override
    public String toString() {
        return "Konkurrence " + compName + "\n" + "Dato: " + compDate + "\n" + "Svømmer fra klubben: "
                + participant.getFullName() + "\n" + "Bedste tid: " + swimTime + " sek.";
    }
}
