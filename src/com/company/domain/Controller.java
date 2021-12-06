package com.company.domain;

import com.company.accounting.MembershipFee;
import com.company.database.FileHandler;
import com.company.domain.Member.CompSwimmer;
import com.company.domain.Member.Disciplin;
import com.company.domain.Member.Member;
import com.company.domain.Member.MemberController;
import com.company.domain.User.Role;
import com.company.domain.User.User;
import com.company.domain.User.UserController;
import com.company.ui.UserInterface;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Controller {
    boolean isRunning = true;

    private final UserInterface ui = new UserInterface();
    private final FileHandler fh = new FileHandler();
    private final MemberController m = new MemberController();
    private final MembershipFee membershipFee = new MembershipFee();
    private final UserController u = new UserController();

    public void start() throws IOException {
        m.loadMembersFromFile();
        m.loadCompSwimmerFromFile();
        while (isRunning) {
            ui.menu();
            String userInput = ui.userInput();
            switch (userInput) {
                case "0":
                    exit();
                    break;

                case "1":
                    //opret medlem
                    createUser();
                    break;

                case "2":
                    //log ind på eksisterende bruger
                    login();
                    break;

                case "3":
                    //opret en bruger
                    createMember();
                    break;

                case "4":
                    //lav en medlem om til en konkurrencesvømmer
                    changeToCompSwimmer();
                    break;

                case "5":
                    //kasserer menu
                    cashierMenu();
                    break;

                case "6":
                    //træner menu
                    coachMenu();
                    break;

                case "7":
                    //se alle medlemmer fra fil
                    seeMembers();
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
            User user = new User(fullName, Role.FORMAND);
            u.saveUser(user);
            ui.printMessage(user.toString());
        } else if (choice == 2) {
            User user = new User(fullName, Role.KASSERER);
            u.saveUser(user);
            ui.printMessage(user.toString());
        } else if (choice == 3) {
            User user = new User(fullName, Role.TRÆNER);
            u.saveUser(user);
            ui.printMessage(user.toString());
        }
    }


    private void login() throws IOException {
        ui.printMessage("Type in user name: ");
        String name = ui.userInput();
        User user = u.findUser(name);
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
        ui.printMessage("Ønsker du at skifte til konkurrencesvømmer? (j/n)");
        String input = ui.userInput();
        if (input.equalsIgnoreCase("J")) {
            ui.printMessage("Indtast navnet på medlemmet du gerne vil konvertere til konkurrencesvømmer:");
            String memberName = ui.userInput();
            Member member = m.findMember(memberName);
            if (member == null) {
                ui.printMessage("Personen eksisterer ikke i databasen.");
            } else {
                ui.printMessage("""
                        Vælg disciplin:
                        1) Butterfly
                        2) Crawl
                        3) Backcrawl
                        4) Breaststroke\n""");

                int choice = ui.userIntput();
                ui.userInput();
                if (choice == 1) {
                    CompSwimmer compSwimmer = new CompSwimmer(member.getFullName(), member.getAge(), member.getMembershipStatus(), Disciplin.BUTTERFLY);
                    m.saveCompSwimmer(compSwimmer);
                    ui.printMessage(compSwimmer.toString());
                } else if (choice == 2) {
                    CompSwimmer compSwimmer = new CompSwimmer(member.getFullName(), member.getAge(), member.getMembershipStatus(), Disciplin.CRAWL);
                    m.saveCompSwimmer(compSwimmer);
                    ui.printMessage(compSwimmer.toString());
                } else if (choice == 3) {
                    CompSwimmer compSwimmer = new CompSwimmer(member.getFullName(), member.getAge(), member.getMembershipStatus(), Disciplin.BACKCRAWL);
                    m.saveCompSwimmer(compSwimmer);
                    ui.printMessage(compSwimmer.toString());
                } else if (choice == 4) {
                    CompSwimmer compSwimmer = new CompSwimmer(member.getFullName(), member.getAge(), member.getMembershipStatus(), Disciplin.BREASTSTROKE);
                    m.saveCompSwimmer(compSwimmer);
                    ui.printMessage(compSwimmer.toString());
                }
            }
        }
    }


    private void cashierMenu() throws IOException {
        boolean run = true;

        while (run) {
            ui.cashierMenu();
            String userInput = ui.userInput();
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
                    //Noter medlem som betalt
                    noteMemberAsPaid();
                    break;

                case "5":
                    //Se det årlige forventede kontigentindbetaling
                    yearlyExpectedKontingentFee();
                    break;

                case "6":
                    //Slet alle betalte kontigent og restance regninger fra systemet
                    deleteAllPaidKontingentsAndRestance();
                    break;

                default:
                    ui.userInputNotValid();
                    break;
            }
        }
    }

    private void seeMembers() {
        m.readMembersFromFile();
        m.readCompSwimmersFromFile();
    }


    private void topFive() {
    }

    public void exit() throws FileNotFoundException {
        m.addMembersToFile();
        m.addCompSwimmerToFile();
        isRunning = false;
    }


    public void chargeKontingent() {
        if (membershipFee.checkMemberFileForMembers() == true) {
            ui.printMessage("""
                (1) Opret kontigent for enkeltstående medlem
                (2) Opret kontigent for alle medlemmer""");

            String userInput = ui.userInput();
            switch (userInput) {

                case "1" -> chargeMember();
                case "2" -> membershipFee.chargeAllMembers();
                default -> ui.userInputNotValid();
            }
        } else {
            ui.printMessage("Der eksistere ikke nogen medlemmer i databasen\n");
        }
    }

    public void chargeMember() {
        ui.printMessage("Indtast navnet på personen: ");
        String nameOfMember = ui.userInput();

        if (membershipFee.memberExistInFile(nameOfMember, "mF")) {
            membershipFee.chargeMember(nameOfMember);
            ui.printMessage("Kontigent oprettet for " + nameOfMember + "\n");
        } else {
            ui.printMessage("Kunne ikke finde " + nameOfMember + " i systemet");
            ui.printMessage("Ønsker du at se en liste over medlemmer i systemet? (y/n) ");
            showListOfMembers("mF");
        }
    }

    public void changeMemberContigentToRestance() {
        if (membershipFee.getAllKontingents().size() > 0) {
            ui.printMessage("""
                (1) Sæt enkeltstående medlem i restance
                (2) Sæt alle medlemmer i restance""");

            String userInput = ui.userInput();
            switch (userInput) {

                case "1" -> memberToRestance();
                case "2" -> membershipFee.allMembersToRestance();
                default -> ui.userInputNotValid();
            }
        } else {
            ui.printMessage("Der er ingen medlemmer at sætte i restance\n");
        }
    }

    public void memberToRestance() {
        ui.printMessage("Indtast navnet på personen: ");
        String nameOfMember = ui.userInput();

        if (membershipFee.memberExistInFile(nameOfMember, "kF")) {
            membershipFee.memberToRestance(nameOfMember);
            ui.printMessage(nameOfMember + " er nu sat i restance\n");
        } else {
            ui.printMessage("Der er ingen medlemmer af navnet " + nameOfMember + ", som har manglende kontigentbetaling");
            ui.printMessage("Ønsker du at se en liste over medlemmer med manglende kontigentbetaling? (y/n) ");
            showListOfMembers("kF");
        }
    }

    public void seeMembersInRestance() {
        if (membershipFee.getAllKontingents().size() > 0) {
            String membersInRestance = membershipFee.getMembersInRestance();
            ui.printMessage(membersInRestance);
        } else {
            ui.printMessage("Der er ingen medlemmer i restance\n");
        }
    }

    public void yearlyExpectedKontingentFee() {
        int expectedKontingent = membershipFee.calculateExpectedKontingent();
        ui.printMessage("Det forventede kontigentindbetaling er " + expectedKontingent + " DKK årligt\n");
    }

    public void deleteAllPaidKontingentsAndRestance() {

        if (membershipFee.checkKontingentsForPaid() == true) {
            membershipFee.deleteAllPaidKontingentsAndRestanceInFile();
            ui.printMessage("De betalte kontigent eller restance regninger er fjernet fra systemet\n");
        } else {
            ui.printMessage("Der er ingen betalte kontigent eller restance regninger i systemet\n");
        }
    }

    public void showListOfMembers(String fileName) {
        String input = ui.userInput();
        switch (input) {

            case "y","yes","Yes","Y" -> ui.printMessage(membershipFee.showListOfMembers(fileName));
            case "n","no","No","N" -> ui.printMessage("Vender tilbage til kasseremenuen...\n");
            default -> ui.userInputNotValid();
        }
    }

    public void noteMemberAsPaid() {
        ui.printMessage("Indtast navnet på medlemmet der har betalt: ");
        String nameOfMember = ui.userInput();

        if (membershipFee.memberExistsAndOwes(nameOfMember)) {
            String memberBills = membershipFee.billsForMemberThatOwes(nameOfMember);
            ui.printMessage(memberBills);
            payBill(nameOfMember);

        } else {
            ui.printMessage("Der er ingen medlemmer med navnet " + nameOfMember + " som har manglende kontigent eller restance betalinger\n");
        }
    }

    public void payBill(String nameOfMember) {
        ui.printMessage("""
         Ønsker du at:
         (1) Marker alle regninger for medlemmet som betalt
         (2) marker enkel regning for medlemmet som betalt""");

        String userInput = ui.userInput();
        switch (userInput) {

            case "1":
                membershipFee.payAllBills(nameOfMember);
                ui.printMessage("Alle " + nameOfMember + "'s regninger er nu markeret som betalt\n");
                break;

            case "2":
                //TODO implement
                ui.printMessage("Not yet implemented\n");
                break;

            default:
                ui.userInputNotValid();
        }
    }

    private void coachMenu() throws IOException {
        boolean run = true;

        while (run) {
            ui.coachMenu();
            String userInput = ui.userInput();
            switch (userInput) {
                case "0":
                    run = false;
                    start();
                    break;

                case "1":
                    //se top 5 konkurrencesvømmer statistik ud fra den bedste tid
                    topFive();
                    break;

                case "2":
                    //gør at træner kan redigere konkurrencesvømmerens stats
                    break;

                default:
                    ui.userInputNotValid();
                    break;
            }

        }
    }

}
