package com.company.ui;

import java.util.Scanner;

public class UserInterface {

    Scanner scanner = new Scanner(System.in);
    public void menu(){
        printMessage("""
                (0) Luk programmet
                (1) Opret bruger
                (2) Log ind
                (3) Opret bruger
                (4) Skift til konkurrencesvømmer
                (5) Se forventet kontingent
                (6) Se medlemmer
                (7) Se top 5""");
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
