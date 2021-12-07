package com.company.ui;

import java.util.Scanner;

public class UserInterface {

    Scanner scanner = new Scanner(System.in);

    public void menu() {
        printMessage("""
                (0) Luk programmet
                (1) Opret bruger
                (2) Log ind
                (3) Opret medlem
                (4) Skift til konkurrencesvømmer
                (5) Gå til kassere menu
                (6) Gå til træner menu
                (7) Se medlemmer""");
    }

    public void cashierMenu() {
        printMessage("""
                (0) Gå tilbage til hovedmenuen
                (1) Opret kontingent
                (2) Sæt medlem i restance
                (3) Se medlemmer i restance
                (4) Medlem betalt for kontingent eller restance
                (5) Se det årlige forventede kontingentindbetaling
                (6) Slet betalte kontingent- og restanceregninger fra systemet""");
    }

    public void coachMenu() {
        printMessage("""
                (0) Gå tilbage til hovedmenuen
                (1) Se top 5
                (2) Opret konkurrence
                (3) Sæt stats for svømmer
                (4) Se nuværende tider for svømmere
                (5) Se konkurrencer""");
    }

    public void userInputNotValid() {
        System.out.println("Userinput not valid...\n");
    }

    public String userInput() {
        return scanner.nextLine();
    }

    public int userIntput() {
        return scanner.nextInt();
    }

    public double userDoubleput() {
        return scanner.nextDouble();
    }

    public void printMessage(String s) {
        System.out.println(s);
    }

    public void printArray(String[] s) {
        System.out.println(s);
    }

}
