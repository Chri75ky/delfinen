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
                (6) Se medlemmer
                (7) Se top 5""");
    }

    public void cashierMenu() {
        printMessage("""
                (0) Gå tilbage til hovedmenuen
                (1) Opret kontigent
                (2) Sæt medlem i restance
                (3) Se medlemmer i restance
                (4) Medlem betalt for kontigent eller restance
                (5) Se det årlige forventede kontigentindbetaling""");
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

    public void printMessage(String s) {
        System.out.println(s);
    }
}
