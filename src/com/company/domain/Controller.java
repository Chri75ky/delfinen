package com.company.domain;

import com.company.accounting.MembershipFee;
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
                    //gemmer alle ændringer og slutter programmet
                    exit();
                    break;

                case "1":
                    //opretter en bruger
                    createUser();
                    break;

                case "2":
                    //logger ind på en allerede oprettet og gemt bruger
                    login();
                    break;

                case "3":
                    //opretter et medlem
                    createMember();
                    break;

                case "4":
                    //laver et medlem om til en konkurrencesvømmer
                    changeToCompSwimmer();
                    break;

                case "5":
                    //viser kasserer menu
                    cashierMenu();
                    break;

                case "6":
                    //viser træner menu
                    coachMenu();
                    break;

                case "7":
                    //viser alle medlemmer, både almindelige og konkurrencesvømmere
                    seeMembers();
                    break;

                case "8":
                    //gemmer de seneste ændringer der laves mens programmet kører
                    saveToFile();
                    break;

                default:
                    ui.userInputNotValid();
                    break;
            }
        }
    }

    //gemmer de seneste ændringer der laves mens programmet kører
    private void saveToFile() throws FileNotFoundException {
        m.addMembersToFile();
        m.addCompSwimmerToFile();
        m.addTopFiveToFile();
        ui.printMessage("Gemt!");
    }

    //opretter en bruger og gemmer den
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
        //Assigner et enum til Brugeren alt efter inputtet fra brugerens valg
        //sætter brugerens navn til det indtastede ovenover og gemmer
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
        } else ui.printMessage("Du har indtastet noget ugyldigt!");
    }

    //kan logge ind på en bruger som allerede er blevet oprettet og indlæst til/fra filen ved at bruge metoden saveToFile()
    private void login() {
        ui.printMessage("Type in user name: ");
        String name = ui.userInput();
        User user = u.findUser(name);
        if (user != null) {
            ui.printMessage("Velkommen " + user.getRole() + " " + name);
        } else {
            ui.printMessage("User not found");
        }
    }

    // Metode til at oprette et medlem
    private void createMember() {
        // Henter navn, alder, medlemskabstype
        ui.printMessage("Indtast personens fulde navn:");
        String fullName = ui.userInput();
        ui.printMessage("Indtast alder:");
        int age = ui.userIntput();
        ui.printMessage("Har medlemmet et aktivt medlemskab? (j/n)");
        ui.userInput();
        String membershipStatus = ui.userInput();

        // Laver en boolean for at sætte medlemskabstype til aktiv eller passiv
        boolean activeMembership = false;
        if (membershipStatus.equalsIgnoreCase("J")) {
            activeMembership = true;
        }

        //Opretter medlemmet med det indtastede info
        Member member = new Member(fullName, age, activeMembership);
        m.saveMember(member);
        ui.printMessage(member.toString());
    }

    // Metode til at ændre et almindeligt medlem til en konkurrencesvømmer
    private void changeToCompSwimmer() {
        ui.printMessage("Ønsker du at skifte til konkurrencesvømmer? (j/n)");
        String input = ui.userInput();
        if (input.equalsIgnoreCase("J")) {
            ui.printMessage("Indtast navnet på medlemmet du gerne vil konvertere til konkurrencesvømmer:");
            String memberName = ui.userInput();
            // Tjek hvorvidt personen eksisterer i programmet
            Member member = m.findMember(memberName);
            if (member == null) {
                ui.printMessage("Personen eksisterer ikke i databasen.");
            } else {
                ui.printMessage("""
                        Vælg disciplin:
                        1) Butterfly
                        2) Crawl
                        3) Backcrawl
                        4) Breaststroke
                        """);

                int choice = ui.userIntput();
                ui.userInput();
                // Sæt en disciplin til brugeren ud fra hvad de har valgt
                if (choice == 1) {
                    CompSwimmer compSwimmer = new CompSwimmer(member.getFullName(), member.getAge(), member.getMembershipStatus(), Disciplin.BUTTERFLY);
                    m.saveCompSwimmer(compSwimmer);
                    m.removeMember(member);
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
                } else ui.printMessage("Du har indtastet noget ugyldigt!");
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
                    // Opret kontingent for enkeltstående medlem eller alle medlemmer
                    chargeKontingent();
                    break;

                case "2":
                    // Ændre medlem eller alle medlemmer med manglende kontingentbetaling til restance
                    changeMemberContingentToRestance();
                    break;

                case "3":
                    // Se medlemmer i restance
                    seeMembersInRestance();
                    break;

                case "4":
                    // Noter medlem som betalt
                    noteMemberAsPaid();
                    break;

                case "5":
                    // Se det årlige forventede kontingentindbetaling
                    yearlyExpectedKontingentFee();
                    break;

                case "6":
                    // Slet alle betalte kontingent og restance regninger fra systemet
                    deleteAllPaidKontingentsAndRestance();
                    break;

                default:
                    ui.userInputNotValid();
                    break;
            }
        }
    }

    // Viser både medlemmer og svømmere fra deres respektive filer
    private void seeMembers() {
        m.readMembersFromFile();
        m.readCompSwimmersFromFile();
    }

    //viser listen af top fem konkurrencesvømmere
    private void topFive() {
        ui.printMessage("De fem bedste tider indenfor alle konkurrencesvømmerne er: ");
        m.readTopFiveFromFile();

    }

    // Gemmer medlemmer og svømmere ved lukning af program
    public void exit() throws FileNotFoundException {
        saveToFile();
        isRunning = false;
    }

    //
    public void chargeKontingent() {
        if (membershipFee.checkMemberFileForMembers()) {
            ui.printMessage("""
                    (1) Opret kontingent for enkeltstående medlem
                    (2) Opret kontingent for alle medlemmer""");

            String userInput = ui.userInput();
            switch (userInput) {

                case "1" -> chargeMember();
                case "2" -> {
                    membershipFee.chargeAllMembers();
                    int amountOfMembers = membershipFee.amountOfMembersInFile();
                    ui.printMessage("Kontingent oprettet for " + amountOfMembers + " medlemmer\n");
                }
                default -> ui.userInputNotValid();
            }
        } else {
            ui.printMessage("Der eksistere ikke nogen medlemmer i databasen\n");
        }
    }

    //
    public void chargeMember() {
        ui.printMessage("Indtast navnet på personen: ");
        String nameOfMember = ui.userInput();

        if (membershipFee.memberExistInFile(nameOfMember, "mF")) {
            membershipFee.chargeMember(nameOfMember);
            ui.printMessage("Kontingent oprettet for " + nameOfMember + "\n");
        } else {
            ui.printMessage("Kunne ikke finde " + nameOfMember + " i systemet");
            ui.printMessage("Ønsker du at se en liste over medlemmer i systemet? (y/n) ");
            showListOfMembers("mF");
        }
    }

    //
    public void changeMemberContingentToRestance() {
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

    //
    public void memberToRestance() {
        ui.printMessage("Indtast navnet på personen: ");
        String nameOfMember = ui.userInput();

        if (membershipFee.memberExistInFile(nameOfMember, "kF")) {
            membershipFee.memberToRestance(nameOfMember);
            ui.printMessage(nameOfMember + " er nu sat i restance\n");
        } else {
            ui.printMessage("Der er ingen medlemmer af navnet " + nameOfMember + ", som har manglende kontingentbetaling");
            ui.printMessage("Ønsker du at se en liste over medlemmer med manglende kontingentbetaling? (y/n) ");
            showListOfMembers("kF");
        }
    }

    //
    public void seeMembersInRestance() {
        if (membershipFee.getAllKontingents().size() > 0) {
            String membersInRestance = membershipFee.getMembersInRestance();
            ui.printMessage(membersInRestance);
        } else {
            ui.printMessage("Der er ingen medlemmer i restance\n");
        }
    }

    //
    public void yearlyExpectedKontingentFee() {
        int expectedKontingent = membershipFee.calculateExpectedKontingent();
        ui.printMessage("Det forventede kontingentindbetaling er " + expectedKontingent + " DKK årligt\n");
    }

    //
    public void deleteAllPaidKontingentsAndRestance() {

        if (membershipFee.checkKontingentsForPaid()) {
            membershipFee.deleteAllPaidKontingentsAndRestanceInFile();
            ui.printMessage("De betalte kontingent eller restance regninger er fjernet fra systemet\n");
        } else {
            ui.printMessage("Der er ingen betalte kontingent eller restance regninger i systemet\n");
        }
    }

    //
    public void showListOfMembers(String fileName) {
        String input = ui.userInput();
        switch (input) {

            case "y", "yes", "Yes", "Y" -> ui.printMessage(membershipFee.showListOfMembers(fileName));
            case "n", "no", "No", "N" -> ui.printMessage("Vender tilbage til kasseremenuen...\n");
            default -> ui.userInputNotValid();
        }
    }

    //
    public void noteMemberAsPaid() {
        ui.printMessage("Indtast navnet på medlemmet der har betalt: ");
        String nameOfMember = ui.userInput();

        if (membershipFee.memberExistsAndOwes(nameOfMember)) {
            String memberBills = membershipFee.billsForMemberThatOwes(nameOfMember);
            ui.printMessage(memberBills);
            payBill(nameOfMember);

        } else {
            ui.printMessage("Der er ingen medlemmer med navnet " + nameOfMember + " som har manglende kontingent eller restance betalinger\n");
        }
    }

    //
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
                    // Se top 5 konkurrencesvømmer statistik ud fra den bedste tid
                    topFive();
                    break;

                case "2":
                    // Opretter en konkurrence for en svømmer
                    createCompetition();
                    break;

                case "3":
                    // Gør at træner kan redigere konkurrencesvømmerens stats
                    setCompSwimmerStats();
                    break;

                case "4":
                    // Viser tider for alle svømmere
                    showCompSwimmerTimes();
                    break;

                case "5":
                    // Viser nuværende konkurrencer i systemet
                    showCompetitions();
                    break;

                default:
                    ui.userInputNotValid();
                    break;
            }

        }
    }

    //viser listen af konkurrencer, hvis der er nogle
    private void showCompetitions() {
        if (m.showCompetitions() == null) {
            ui.printMessage("Ingen konkurrencer lige nu!");
        } else ui.printMessage(m.showCompetitions());
    }

    // Skaber en konkurrence ud fra oplysninger angivet af bruger - træner
    private void createCompetition() {
        ui.printMessage("Hvilken svømmer ønsker du at oprette en konkurrence for?");
        CompSwimmer swimmer = m.findCompSwimmer(ui.userInput());
        if (swimmer != null) {
            ui.printMessage("Hvad er stævnes navn?");
            String compName = ui.userInput();
            ui.printMessage("Hvilken dato blev stævnet afholdt?");
            String compDate = ui.userInput();
            ui.printMessage("Hvilken tid fik svømmeren?");
            double swimTime = ui.userDoubleput();
            ui.userInput();
            Competition newCompetition = new Competition(compName, compDate, swimmer, swimTime);
            ui.printMessage(newCompetition.toString());
            m.addCompetition(newCompetition);
        } else {
            ui.printMessage("Ingen svømmer med det navn!");
        }
    }

    //viser kun listen af konkurrencesvømmere med deres tider også
    private void showCompSwimmerTimes() {
        m.showCompSwimmerTimes();
    }

    // Sætter en tid til en svømmer
    private void setCompSwimmerStats() {
        ui.printMessage("Hvilken svømmer ønsker du at sætte en ny tid til?");
        CompSwimmer swimmerToSetTime = m.findCompSwimmer(ui.userInput());
        if (swimmerToSetTime != null) {
            ui.printMessage("Hvilken tid fik svømmeren?");
            double time = ui.userDoubleput();
            ui.userInput();
            m.setCompSwimmerStats(swimmerToSetTime, time);
            System.out.println(swimmerToSetTime.getFullName() + " " + swimmerToSetTime.getTime() + " sek. " + swimmerToSetTime.getDiscipline());

        } else {
            ui.printMessage("Svømmer ikke fundet!");
        }
    }
}
