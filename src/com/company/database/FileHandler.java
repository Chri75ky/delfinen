package com.company.database;

import com.company.domain.Member.Member;
import com.company.domain.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileHandler {
    private ArrayList<User> users = new ArrayList<>();


    //TODO måske merge user metoderne med members metoderne, da de begge gør stort set de samme ting, bare til forskellige arrayLister
    //--------------------------------------------------------------
    //USERS
    //--------------------------------------------------------------

    public void addUserToFile(User user) throws FileNotFoundException {
        PrintStream ps = new PrintStream(new FileOutputStream("UserList.txt", true));
        Scanner out = new Scanner("UserList.txt");


        while (out.hasNextLine()) {
            out.nextLine();
        }

        // Tilføjer user til "UserList.txt"
        ps.append("\n" + "Navn: " + user.getFullName() + "\n" + "Rolle: " + user.getRole() + "\n");

        //TODO Fjern user fra arraylist?
    }

    //tilføjer en bruger til arrayListen users
    public void saveUser(User user) throws FileNotFoundException {
        users.add(user);
        addUserToFile(user);
    }

    //sletter user fra arrayList users
    public void deleteUser(User user) {
        users.remove(user);
    }


    //TODO INPROGRESS prøver at lave sådan at den kan finde brugeren direkte fra textfilen
    //finder en bruger i brugerListen, ved brug af navn
    public User findUser(String userName) throws IOException {
       /* for (int i = 0; i < users.size(); i++) {
            if (userName.equalsIgnoreCase(users.get(i).getFullName())) {
                return users.get(i);
            } else return null;
        }
        return null;*/

        return null;
    }

    //TODO lav at den henter users fra filen
    //printer arrayList ud
    public String seeUsers() {
        return users.toString();
    }

    public StringBuilder showUsersFromFile() throws FileNotFoundException {
        Scanner users = new Scanner(new File("UserList.txt"));
        StringBuilder allUsers = new StringBuilder();

        while (users.hasNextLine()) {
            allUsers.append(users.nextLine() + "\n");
        }
        return allUsers;
    }




    // Var lidt i tvivl om kontigent og restance metoderne skal være under FileHandler, men så bare vi havde en arraylist med members her
    public int getTotalContigent() {
        int totalContigent = 0;
        for (Member m : members) {
            totalContigent += m.getKontigent().getPrice();
        }
        return totalContigent;
    }

    public StringBuilder seeMembersWithRestance() {
        StringBuilder str = new StringBuilder();
        List<Member> memberInRestance = getMembersInRestance();
        int i = 1;

        for (Member m : memberInRestance) {
            str.append(i + ") " + m.getFullName() + " har en restance af: " + m.getKontigent().getRestance() + " DKK\n");
            i++;
        }
        return str;
    }

    private List getMembersInRestance() {
        List<Member> membersInRestance = new ArrayList<>();

        for (Member m : members) {
            if (m.getKontigent().getRestance() > 0) {
                membersInRestance.add(m);
            }
        }
        return membersInRestance;
    }

    public StringBuilder seeMembersWithContigent() {
        StringBuilder str = new StringBuilder();
        List<Member> memberWithContigent = getMembersWithContigent();
        int i = 1;

        for (Member m : memberWithContigent) {
            str.append(i + ") " + m.getFullName() + " mangler at betale: " + m.getKontigent().getPrice() + " DKK i kontigent\n");
            i++;
        }
        return str;
    }

    private List getMembersWithContigent() {
        List<Member> membersWithContigent = new ArrayList<>();

        for (Member m : members) {
            if (m.getKontigent().getPrice() > 0) {
                membersWithContigent.add(m);
            }
        }
        return membersWithContigent;
    }

    public void changeMemberFromContigentToRestance(int input) {
        List<Member> memberWithContigent = getMembersWithContigent();
        memberWithContigent.get(input).getKontigent().changePriceToRestance();
    }

    public StringBuilder seeMembersWithContigentAndRestance() {
        StringBuilder str = new StringBuilder();
        List<Member> memberWithContigentOrRestance = getMembersWithContigentOrRestance();
        int i = 1;

        for (Member m : memberWithContigentOrRestance) {
            str.append(i + ") " + m.getFullName() + ", Manglende betaling af:\tkontigent: " + m.getKontigent().getPrice() + " DKK,\tRestance: " + m.getKontigent().getRestance() + " DKK\n");
            i++;
        }

        return str;
    }

    private List getMembersWithContigentOrRestance() {
        List<Member> memberWithContigentOrRestance = getMembersWithContigent();
        memberWithContigentOrRestance.addAll(getMembersInRestance());
        return memberWithContigentOrRestance;
    }

    public void memberPayedForContigentOrRestance(int member, int payment) {
        List<Member> memberWithContigentOrRestance = getMembersWithContigentOrRestance();
        int paymentFromMember = payment;

        //TODO Gør så member først betaler for restance , og hvis der er flere penge betaler for kontigent (hvis member skylder noget) og ellers får penge tilbage hvis der er flere penge i payment

        if (memberWithContigentOrRestance.get(member).getKontigent().getRestance() > 0) {


        } else {

        }

    }



}
