package com.company.domain;

import com.company.accounting.MembershipFee;
import com.company.database.FileHandler;
import com.company.domain.Member.CompSwimmer;
import com.company.domain.Member.Member;
import com.company.domain.Member.MemberController;
import com.company.ui.UserInterface;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Controller {
    boolean isRunning = true;
    private UserInterface ui = new UserInterface();
    private FileHandler fh = new FileHandler();
    private MemberController m = new MemberController();
    private MembershipFee membershipFee = new MembershipFee();

    public void start() throws IOException {
        while (isRunning) {
            ui.menu();
            String userInput = ui.userInput();
            switch (userInput) {
                case "0":
                    exit();
                    break;
                case "1":
                    createUser();
                    break;
                case "2":
                    login();
                    break;
                case "3":
                    createMember();
                    break;
                case "4":
                    changeToCompSwimmer();
                    break;
                case "5":
                    cashierMenu();
                    break;
                case "6":
                    seeMembers();
                    break;
                case "7":
                    topFive();
                    break;

                default:
                    ui.userInputNotValid();
                    break;
            }
        }
    }

    //TODO lav oprettelse af user og gem til arrayList
    private void createUser() throws FileNotFoundException {
        ui.printMessage("Indtast brugerens fulde navn:");
        String fullName = ui.userInput();
        ui.printMessage("""
                Vælg venligst hvilken rolle brugeren har:
                1) Formand
                2) Kasserer
                3) Træner""");
        int choice = ui.userIntput();
        ui.userInput();
        if (choice == 1) {
            User user = new User(fullName, User.Role.FORMAND);
            fh.saveUser(user);
            ui.printMessage(user.toString());
        } else if (choice == 2) {
            User user = new User(fullName, User.Role.KASSERER);
            fh.saveUser(user);
            ui.printMessage(user.toString());
        } else if (choice == 3) {
            User user = new User(fullName, User.Role.TRÆNER);
            fh.saveUser(user);
            ui.printMessage(user.toString());
        }
    }


    private void login() throws IOException {
        ui.printMessage("Type in user name: ");
        String name = ui.userInput();
        User user = fh.findUser(name);
        if(user != null){
            ui.printMessage("Velkommen " + user.getRole() + " " + name);
        } else {
            ui.printMessage("User not found");
        }
    }

    //metode til at oprette et medlem
    private void createMember() throws FileNotFoundException {
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
            m.saveMember(member);
            ui.printMessage(member.toString());

    }

    //metode til at lave et almindeligt medlem om til en konkurrencesvømmer
    private void changeToCompSwimmer() throws FileNotFoundException {
        ui.printMessage("Ønsker du at skifte til konkurrencesvømmer? (j/n");
        String input = ui.userInput();
        if (input.equalsIgnoreCase("J")) {
            ui.printMessage("Indtast navnet på medlemmet du gerne vil konvertere til konkurrencesvømmer:");
            String memberName = ui.userInput();
            Member member = m.findMember(memberName);
            if (member == null) {
                ui.printMessage("Personen eksisterer ikke i databasen.");
            } else {
                ui.printMessage("Vælg disciplin:");
                String disciplin = ui.userInput();
                CompSwimmer newMember = new CompSwimmer(member.getFullName(),member.getAge(),member.getMembershipStatus(),disciplin);
                m.saveMember(newMember);
                ui.printMessage(newMember.toString());
            }
        }
    }


    private void cashierMenu() throws IOException {
        boolean run = true;

        while (run) {
            ui.cashierMenu();
            String userInput = ui.userInput();
            System.out.println("Sss");
            switch (userInput) {
                case "0":
                    run = false;
                    start();
                    break;

                case "1":
                    //Opret kontigent for enkeltstående medlem eller alle medlemmer
                    chargeKontingent();
                    break;

                case "2":
                    //Ændre medlem eller alle medlemmer med manglende kontigentbetaling til restance
                    changeMemberContigentToRestance();
                    break;

                case "3":
                    //Se medlemmer i restance
                    seeMembersInRestance();
                    break;

                case "4":
                    //TODO Medlem betalt for restance/kontigent
                    //memberPayed();
                    break;

                case "5":
                    //Se det årlige forventede kontigentindbetaling
                    yearlyExpectedKontingentFee();
                    break;

                default:
                    ui.userInputNotValid();
                    break;
            }
        }
    }

    private void seeMembers() throws FileNotFoundException {
        //StringBuilder members = showMembersFromFile();
        //System.out.print(members);
    }

    private void seeUsers() throws FileNotFoundException {
        StringBuilder users = fh.showUsersFromFile();
        System.out.print(users);
    }

    private void topFive() {
    }

    public void exit() throws FileNotFoundException {
        m.addMembersToFile();
        isRunning = false;
    }


    public void chargeKontingent() {
        ui.printMessage("""
                (1) Opret kontigent for enkeltstående medlem
                (2) Opret kontigent for alle medlemmer""");

        String userInput = ui.userInput();
        switch (userInput) {

            case "1" -> chargeMember();
            case "2" -> membershipFee.chargeAllMembers();
            default -> ui.userInputNotValid();
        }
    }

    public void chargeMember() {
        ui.printMessage("Indtast navnet på personen: ");
        String nameOfMember = ui.userInput();
        membershipFee.chargeMember(nameOfMember);
    }

    public void changeMemberContigentToRestance() {
        ui.printMessage("""
                (1) Sæt enkeltstående medlem i restance
                (2) Sæt alle medlemmer i restance""");

        String userInput = ui.userInput();
        switch (userInput) {

            case "1" -> memberToRestance();
            case "2" -> membershipFee.allMembersToRestance();
            default -> ui.userInputNotValid();
        }
    }

    public void memberToRestance() {
        ui.printMessage("Indtast navnet på personen: ");
        String nameOfMember = ui.userInput();
        membershipFee.memberToRestance(nameOfMember);
    }

    public void seeMembersInRestance() {
        String membersInRestance = membershipFee.getMembersInRestance();
        ui.printMessage(membersInRestance);
    }

    public void yearlyExpectedKontingentFee() {
        int expectedKontingent = membershipFee.calculateExpectedKontingent();
        ui.printMessage("Det forventede kontigentindbetaling er " + expectedKontingent + " DKK årligt\n");
    }

}
