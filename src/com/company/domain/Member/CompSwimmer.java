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


    //metode brugt til at sammenligner konkurrencesvømmernes tider, bruges til at lave top 5 liste
    public int compareTo(CompSwimmer otherCompSwimmer) {
        if (this.time < otherCompSwimmer.time) {
            return -1;
        } else {
            return 1;
        }
    }
}
