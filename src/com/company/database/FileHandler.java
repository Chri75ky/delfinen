package com.company.database;

import com.company.domain.Member;
import com.company.domain.User;

import java.util.ArrayList;
import java.util.List;

public class FileHandler {
    ArrayList<Member> members = new ArrayList<>();
    ArrayList<User> users = new ArrayList<>();


    //--------------------------------------------------------------
    //MEMBERS
    //--------------------------------------------------------------
    //tilføjer medlem til arrayList
    public void saveMember(Member member) {
        members.add(member);
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

    //--------------------------------------------------------------
    //USERS
    //--------------------------------------------------------------

    //tilføjer en bruger til arrayListen users
    public void saveUser(User user) {
        users.add(user);
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
