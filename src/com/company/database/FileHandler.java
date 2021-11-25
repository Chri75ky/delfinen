package com.company.database;

import com.company.domain.Member;
import com.company.domain.User;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileHandler {
    private ArrayList<Member> members = new ArrayList<>();
    private ArrayList<User> users = new ArrayList<>();




    //--------------------------------------------------------------
    //MEMBERS
    //--------------------------------------------------------------

    // Tilføjer et medlem til en liste ude fra programmet
    public void addMemberToFile(Member member) throws FileNotFoundException {
        PrintStream ps = new PrintStream(new FileOutputStream("MemberList.txt", true));
        Scanner out = new Scanner("MemberList.txt");


        while (out.hasNextLine()) {
            out.nextLine();
        }

        // Tilføjer medlemmet til "MemberList.txt"
        ps.append("\n" + "Navn: " + member.getFullName() + "\n" + "Alder: " + member.getAge() + "\n" + "Aktivt medlemskab: " + member.getMembershipStatus() + "\n" + "Hold: " + member.getTeam() + "\n");

        //TODO Fjern medlemmet fra arraylisten?
    }

    //tilføjer medlem til arrayList
    public void saveMember(Member member) throws FileNotFoundException {
        members.add(member);
        addMemberToFile(member);
    }

    //printer arrayList ud
    public String seeMembers() {
        return members.toString();
    }

    //finder et medlem i listen ved brug af navnet, returnerer et medlem
    public Member findMember(String memberName) {
        for (int i = 0; i < members.size(); i++) {
            if (memberName.equalsIgnoreCase(members.get(i).getFullName())) {
                return members.get(i);
            } else return null;
        }
        return null;
    }

    //sletter medlem fra arrayList members
    public void deleteMember(Member member) {
        members.remove(member);
    }


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

    //finder en bruger i brugerListen, ved brug af navn
    public User findUser(String userName) {
        for (int i = 0; i < users.size(); i++) {
            if (userName.equalsIgnoreCase(users.get(i).getFullName())) {
                return users.get(i);
            } else return null;
        }
        return null;
    }

    //printer arrayList ud
    public String seeUsers() {
        return users.toString();
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

        for (Member m : memberInRestance) {
            str.append(m.getFullName() + " has a restance of: " + m.getKontigent().getRestance() + "\n");
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

}
