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
                (4) Se forventet kontingent
                (5) Se medlemmer
                (6) Se top 5""");
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
