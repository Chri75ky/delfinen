package com.company.domain.Member;

import com.company.domain.Kontingent;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Member {

    protected String fullName;
    protected int age;
    protected boolean activeMembership;
    protected String team;
    private Kontingent kontingent = new Kontingent();

    public Member(String fullName, int age, boolean activeMembership) {
        this.fullName = fullName;
        this.age = age;
        this.activeMembership = activeMembership;
        if (age < 18) {
            this.team = "Junior";
        } else this.team = "Senior";

        makeKontigent();
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
        return "Navn: " + fullName + ", Medlemstype: " + activeMembership + ", Hold: " + team;
    }

        public void addMember (Member member){
            members.add(member);
        }


        public void deleteMember(int memberIndex){
            members.remove(members.get(memberIndex));
        }

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

    //Getters
    public String getFullName() {
        return fullName;
    }

    public int getAge() {
        return age;
    }

    public boolean getMembershipStatus() {
        return activeMembership;
    }

    public String getTeam() {
        return team;
    }

    public Kontingent getKontigent() {
        return this.kontingent;
    }

    public void makeKontigent() {
        this.kontingent.setKontigent(this.age, this.activeMembership);
    }

    @Override
    public String toString() {
        return "Medlem: " + fullName + "\n" + "Alder: " + age + "\n" + "Aktivt medlemskab: " + activeMembership + "\n" + "Hold: " + team;
    }
}
