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
                    changeToCompSwimmer();
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

    //TODO lav oprettelse af user og gem til arrayList
    private void createUser() {

    }

    //TODO lav login til user
    private void login() {

    }

    //metode til at oprette et medlem
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

            Member member = new Member(fullName, age, activeMembership);
            fh.saveMember(member);
            ui.printMessage(member.toString());

    }

    //metode til at lave et almindeligt medlem om til en konkurrencesvømmer
    private void changeToCompSwimmer() {
        ui.printMessage("Ønsker du at skifte til konkurrencesvømmer? (j/n");
        String input = ui.userInput();
        if (input.equalsIgnoreCase("J")) {
            ui.printMessage("Indtast navnet på medlemmet du gerne vil konvertere til konkurrencesvømmer:");
            String memberName = ui.userInput();
            Member member = fh.findMember(memberName);
            if (member == null) {
                ui.printMessage("Personen eksisterer ikke i databasen.");
            } else {
                ui.printMessage("Vælg disciplin:");
                String disciplin = ui.userInput();
                CompSwimmer newMember = new CompSwimmer(member.fullName,member.age,member.activeMembership,disciplin);
                fh.saveMember(newMember);
                fh.deleteMember(member);
                ui.printMessage(newMember.toString());
            }
        }
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
