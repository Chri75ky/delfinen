package com.company.domain.Member;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MemberController {
    private final Member m = new Member(null, 0, true);

    public MemberController() {

    }

    //getting text from file
    private final ArrayList<Member> members = new ArrayList<>();

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

    public void addMember(Member member) {
        members.add(member);
    }


    public void deleteMember(int memberIndex) {
        members.remove(members.get(memberIndex));
    }

    // Tilføjer et medlem til en liste ude fra programmet
    public void addMembersToFile() throws FileNotFoundException {
        PrintStream ps = new PrintStream(new FileOutputStream("data/Medlemmer.csv", true));
        Scanner out = new Scanner("data/Medlemmer.csv");


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

    public Member findMember(String name) {
        for (Member member : members) {
            if (member.getFullName().equals(name)) {
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

    // Kode fundet her : https://stackoverflow.com/questions/10960213/how-can-i-read-comma-separated-values-from-a-text-file-in-java
    public void showMembersFromString() {
        try (BufferedReader in = new BufferedReader(new FileReader("data/Medlemmer.csv"))) {
            String str;
            while ((str = in.readLine()) != null) {
                System.out.println(str);
            }
        } catch (IOException e) {
            System.out.println("File Read Error");
        }
    }

    public void loadMembersFromFile() {

    }

}
