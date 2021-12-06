package com.company.domain.Member;

public class CompSwimmer extends Member {
    private final Disciplin discipline;
    private double bestTime;

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

    public Disciplin getDiscipline() {
        return discipline;
    }

    public double getBestTime() {
        return bestTime;
    }

    public void setBestTime(double bestTime) {
        this.bestTime = bestTime;
    }

    @Override
    public String toString() {
        return fullName + ";" +
                age + ";" +
                activeMembership + ";" +
                team + ";" +
                discipline;
    }
}
