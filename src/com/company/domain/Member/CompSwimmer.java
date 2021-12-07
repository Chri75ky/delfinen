package com.company.domain.Member;

public class CompSwimmer extends Member {
    private final Disciplin discipline;
    private double bestTime;

    public CompSwimmer(String fullName, int age, boolean activeMembership, Disciplin discipline) {
        super(fullName, age, activeMembership);
        this.discipline = discipline;
    }

    public CompSwimmer(String fullName, int age, boolean activeMembership, Disciplin discipline, double bestTime) {
        super(fullName, age, activeMembership);
        this.discipline = discipline;
        this.bestTime = bestTime;
    }

    public String toCSV() {
        return fullName + ";" +
                age + ";" +
                activeMembership + ";" +
                team + ";" +
                discipline + ";" +
                bestTime;
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
                discipline + ";" +
                bestTime;
    }
}
