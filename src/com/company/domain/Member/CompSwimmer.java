package com.company.domain.Member;

public class CompSwimmer extends Member {
    private Disciplin discipline;
    private double time;

    public CompSwimmer(String fullName, int age, boolean activeMembership, Disciplin discipline) {
        super(fullName, age, activeMembership);
        this.discipline = discipline;
    }

    public CompSwimmer(String fullName, int age, boolean activeMembership, Disciplin discipline, double time) {
        super(fullName, age, activeMembership);
        this.discipline = discipline;
        this.time = time;
    }

    public CompSwimmer() {
        super();
    }

    public String toCSV() {
        return fullName + ";" +
                age + ";" +
                activeMembership + ";" +
                team + ";" +
                discipline + ";" +
                time;
    }

    public Disciplin getDiscipline() {
        return discipline;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return fullName + ";" +
                age + ";" +
                activeMembership + ";" +
                team + ";" +
                discipline + ";" +
                time;
    }


    public int compareTo(CompSwimmer otherCompSwimmer) {
        if (this.time < otherCompSwimmer.time) {
            return -1;
        } else {
            return 1;
        }
    }
}
