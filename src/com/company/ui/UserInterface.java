package com.company.ui;

import java.util.Scanner;

public class UserInterface {

    Scanner scanner = new Scanner(System.in);
    public void menu(){
        printMessage("""
                (0) Luk programmet
                (1) Log ind
                (2) Opret bruger
                (3) Skift til konkurrencesvømmer
                (4) Gå til kassere menu
                (5) Se medlemmer
                (6) Se top 5""");
    }

    public void cashierMenu() {
        printMessage("""
                (0) Gå tilbage til hovedmenuen
                (1) Se forventet indbetaling af medlemmers kontigent
                (2) Sæt medlem i restance
                (3) Se medlemmer i restance
                (4) Medlem betalt for kontigent/restance""");
    }

    public void userInputNotValid() {
        System.out.println("Userinput not valid...\n");
    }

    public String userInput(){
        return scanner.nextLine();
    }

    public int userIntput(){
        return scanner.nextInt();
    }

    public void printMessage(String s) {
        System.out.println(s);
    }
}
