package com.company.domain.Member;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class MemberController {
    private final ArrayList<Member> members = new ArrayList<>();
    private final ArrayList<CompSwimmer> compSwimmers = new ArrayList<>();

    // Tilføjer et medlem til en liste ude fra programmet
    public void addMembersToFile() throws FileNotFoundException {
        PrintStream ps = new PrintStream(new FileOutputStream("data/Medlemmer.csv", true));
        Scanner out = new Scanner("data/Medlemmer.csv");

        while (out.hasNextLine()) {
            out.nextLine();
        }

        // Tilføjer medlemmet til filen
        ps.append(memberToCSV());

    }

    public void addCompSwimmerToFile() throws FileNotFoundException {
        PrintStream ps = new PrintStream(new FileOutputStream("data/KonkurrenceSvømmer.csv", true));
        Scanner out = new Scanner("data/KonkurrenceSvømmer.csv");

        while (out.hasNextLine()) {
            out.nextLine();
        }

        // Tilføjer konkurrence svømmeren til filen
        ps.append(compSwimmerToCSV());

    }

    //tilføjer medlem til arrayList
    public void saveMember(Member member) {
        members.add(member);
    }

    public void saveCompSwimmer(CompSwimmer compSwimmer) {
        compSwimmers.add(compSwimmer);
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

    public String memberToCSV() {
        StringBuilder sb = new StringBuilder();

        for (Member member : members) {
            sb.append(member.toCSV()).append('\n');
        }

        return sb.toString();
    }

    public String compSwimmerToCSV() {
        StringBuilder sb = new StringBuilder();

        for (CompSwimmer compSwimmer : compSwimmers) {
            sb.append(compSwimmer.toCSV()).append('\n');
        }

        return sb.toString();
    }

    public String getInfo(int memberIndex) {
        Member member = members.get(memberIndex);
        return member.toString();
    }

    /* Kode fundet her : https://stackoverflow.com/questions/10960213/how-can-i-read-comma-separated-values-from-a-text-file-in-java
     Når medlemmer bliver indlæst fra fil bliver de også skabt som Member objekter igen og sat ind i arrayListen.
     Så nu burde det være muligt at kunne arbejde med members selv efter et reboot af programmet. Så længe at
      metoden er blevet kørt. Der er mulighed for at kunne gøre metoden mindre og måske få tilføjet at den selv indlæser
      ved start af programmet.
     */
    public void loadMembersFromFile() {
        try (BufferedReader in = new BufferedReader(new FileReader("data/Medlemmer.csv"))) {
            String str;
            while ((str = in.readLine()) != null) {
                String[] tokens = str.split(";");
                System.out.println(Arrays.toString(tokens));
                String name = tokens[0];
                int age = Integer.parseInt(tokens[1]);
                boolean membershipStatus;
                if(tokens[2].equalsIgnoreCase("true")) {
                    membershipStatus = true;
                } else {
                    membershipStatus = false;
                }

                Member currentMember = new Member(name, age, membershipStatus);
                members.add(currentMember);
            }
        } catch (IOException e) {
            System.out.println("File Read Error");
        }
    }

}
