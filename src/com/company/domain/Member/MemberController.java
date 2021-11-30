package com.company.domain.Member;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MemberController {
    private Member m = new Member(null, 0, true);

    public MemberController() {

    }

    //getting text from file
    private ArrayList<Member> members = new ArrayList<>();

    public List<Member> getMembers() {
        return members;
    }

    public String getStringOfMembers() {
        int index = 1;
        StringBuilder sb = new StringBuilder();

        for (Member member : members) {
            sb.append(index).append(". ").append(simplePrint()).append('\n');
            index++;
        }

        return sb.toString();
    }

    private String simplePrint() {
        return "Navn: " + m.fullName + ", Medlemstype: " + m.activeMembership + ", Hold: " + m.team;
    }

    public void addMember (Member member){
        members.add(member);
    }


    public void deleteMember(int memberIndex){
        members.remove(members.get(memberIndex));
    }

    // Tilføjer et medlem til en liste ude fra programmet
    public void addMembersToFile() throws FileNotFoundException {
        PrintStream ps = new PrintStream(new FileOutputStream("Medlemmer.csv", true));
        Scanner out = new Scanner("Medlemmer.csv");


        while (out.hasNextLine()) {
            out.nextLine();
        }

        // Tilføjer medlemmet til filen
        ps.append(toCSV());

        //TODO Fjern medlemmet fra arraylisten?
    }

    //tilføjer medlem til arrayList
    public void saveMember(Member member) throws FileNotFoundException {
        members.add(member);
    }

    //TODO gør at den printer medlemmerne ud fra filen.
    //printer arrayList ud
    public String seeMembers() {
        return members.toString();
    }




    //sletter medlem fra arrayList members
    public void deleteMember(Member member) {
        members.remove(member);
    }

    public Member findMember(String name){
        for (Member member : members) {
            if (member.getFullName().equals(name)){
                return member;
            }
        }
        return null;
    }

    public String toCSV() {
        StringBuilder sb = new StringBuilder();

        for (Member member : members) {
            sb.append(member.toCSV()).append('\n');
        }

        return sb.toString();
    }

    public String getInfo(int memberIndex) {
        Member member = members.get(memberIndex);
        return member.toString();
    }

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
