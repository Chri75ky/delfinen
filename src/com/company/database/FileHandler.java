package com.company.database;

import com.company.domain.Member;

import java.util.ArrayList;
import java.util.List;

public class FileHandler {
    ArrayList<Member> members = new ArrayList<>();

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

    //sletter medlem fra arrayList
    public void deleteMember(Member member) {
        members.remove(member);
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
