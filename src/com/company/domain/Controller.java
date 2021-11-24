package com.company.domain;

import com.company.database.FileHandler;
import com.company.ui.UserInterface;

public class Controller {
    boolean isRunning = true;
    private UserInterface ui = new UserInterface();
    private FileHandler fh = new FileHandler();

    public void start() {
        while (isRunning) {
            ui.menu();
            String userInput = ui.userInput();
            switch (userInput) {
                case "0":
                    exit();
                    break;
                case "1":
                    login();
                    break;
                case "2":
                    createMember();
                    break;
                case "3":
                    //changeToCompSwimmer();
                case "4":
                    seeContingent();
                    break;
                case "5":
                    seeMembers();
                    break;
                case "6":
                    topFive();
                    break;
            }
        }
    }


    private void login() {
    }

    private void createMember() {
        //henter navn, alder, medlemskabstype
        ui.printMessage("Indtast personens fulde navn:");
        String fullName = ui.userInput();
        ui.printMessage("Indtast alder:");
        int age = ui.userIntput();
        ui.printMessage("Har medlemmet et aktivt medlemskab? (j/n)");
        ui.userInput();
        String answer = ui.userInput();

        // laver en boolean for at sætte medlemskabstype til aktiv eller passiv
        boolean activeMembership = false;
        String team;
        if (answer.equalsIgnoreCase("J")) {
            activeMembership = true;
        }


        //hvis medlemmet ønsker at være konkurrencesvømmer vil metoden oprette en ny konkurrencesvømmer
        //ellers vil den bare oprette et almindeligt medlem
        //TODO rykke denne del til en metode der laver en member om til konkurrencesvømmer
        ui.printMessage("Ønsker medlemmet at være konkurrencesvømmer? (j/n)");
        String input = ui.userInput();
        if (input.equalsIgnoreCase("J")) {
            ui.printMessage("Hvilken disciplin?");
            String disciplin = ui.userInput();
            CompSwimmer member = new CompSwimmer(fullName, age, activeMembership, disciplin);
            fh.saveMember(member);
            System.out.println(member.toString());
        } else {
            Member member = new Member(fullName, age, activeMembership);
            fh.saveMember(member);
            System.out.println(member.toString());
        }

    }

    private void changeToCompSwimmer(Member member) {

    }

    private void seeContingent() {
    }

    private void seeMembers() {
        System.out.println(fh.seeMembers());
    }

    private void topFive() {
    }

    public void exit() {
        isRunning = false;
    }
}
