package com.company.accounting;

import com.company.database.FileHandler;
import com.company.domain.Member.Member;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MembershipFee {
    FileHandler fh = new FileHandler();
    private static final String MEMBER_FILE = "data/Medlemmer.csv";
    private static final String KONTINGENT_FILE = "data/Kontingent.csv";


    public void chargeMember(String nameOfMember) {
        //Loop igennem Medlemmer.csv filen -> opret kontigent for specifikt medlem -> kontigentet går over i ny fil kontigent.csv

        String line = "";
        String splitBy = ";";


        try {
            //Parsing a CSV file into BufferedReader class constructor
            BufferedReader br = new BufferedReader(new FileReader(MEMBER_FILE));

            while ((line = br.readLine()) != null) {   //Returns a Boolean value
                String[] member = line.split(splitBy);

                if (member[0].contains(nameOfMember)) {
                    String memberName = member[0];
                    int memberAge = Integer.parseInt(member[1]);
                    boolean membershipStatus = Boolean.parseBoolean(member[2]);
                    double price = 0;
                    boolean isPaid = false;
                    boolean inRestance = false;

                    Kontingent newKontingent = new Kontingent(memberName, memberAge, membershipStatus, price, isPaid, inRestance);
                    newKontingent.setKontingentPrice();
                    String newKontingentCSV = newKontingent.toCSV();

                    saveToCSV(KONTINGENT_FILE, newKontingentCSV);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void chargeAllMembers() {
        //Loop igennem Medlemmer.csv filen -> opret kontigent for hvert medlem -> kontigentet går over i ny fil kontigent.csv

        String line = "";
        String splitBy = ";";

        try {
            //Parsing a CSV file into BufferedReader class constructor
            BufferedReader br = new BufferedReader(new FileReader(MEMBER_FILE));

            while ((line = br.readLine()) != null) {   //Returns a Boolean value
                String[] member = line.split(splitBy);

                String memberName = member[0];
                int memberAge = Integer.parseInt(member[1]);
                boolean membershipStatus = Boolean.parseBoolean(member[2]);
                double price = 0;
                boolean isPaid = false;
                boolean inRestance = false;

                Kontingent newKontingent = new Kontingent(memberName, memberAge, membershipStatus, price, isPaid, inRestance);
                newKontingent.setKontingentPrice();
                String newKontingentCSV = newKontingent.toCSV();

                saveToCSV(KONTINGENT_FILE, newKontingentCSV);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int calculateExpectedKontingent() {
        double expectedKontingent = 0;

        String line = "";
        String splitBy = ";";

        try {
            //Parsing a CSV file into BufferedReader class constructor
            BufferedReader br = new BufferedReader(new FileReader(MEMBER_FILE));

            while ((line = br.readLine()) != null) {   //Returns a Boolean value
                String[] member = line.split(splitBy);

                String memberName = member[0];
                int memberAge = Integer.parseInt(member[1]);
                boolean membershipStatus = Boolean.parseBoolean(member[2]);

                Kontingent expKontingent = new Kontingent(memberName, memberAge, membershipStatus);
                expKontingent.setKontingentPrice();

                expectedKontingent += expKontingent.getPrice();

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return (int) Math.round(expectedKontingent);
    }

    public void memberToRestance(String nameOfMember) {
        List<Kontingent> allKontingents = getAllKontingents();

        for (Kontingent kontigent : allKontingents) {
            if (kontigent.getInRestance() == false && kontigent.getMemberName().contains(nameOfMember) && kontigent.getIsPaid() == false) {
                kontigent.setInRestance();
            }
        }

    }

    public void allMembersToRestance() {
        List<Kontingent> allKontingents = getAllKontingents();

        for (Kontingent kontigent : allKontingents) {
            if (kontigent.getInRestance() == false && kontigent.getIsPaid() == false) {
                kontigent.setInRestance();
            }
        }

    }

    //Method that updates file -> first clear file -> go through list and adds them



    private void saveToCSV(String file, String csvLine) {
        try {
            PrintStream ps = new PrintStream(new FileOutputStream(file, true));
            ps.append(csvLine).append("\n");
        } catch (FileNotFoundException e) {
            System.out.println("The file does not exist");
        }
    }

    private List<Kontingent> getAllKontingents() {
        List<Kontingent> allKontingents = new ArrayList<>();
        String line = "";
        String splitBy = ";";

        try {
            //Parsing a CSV file into BufferedReader class constructor
            BufferedReader br = new BufferedReader(new FileReader(KONTINGENT_FILE));

            while ((line = br.readLine()) != null) {   //Returns a Boolean value
                String[] member = line.split(splitBy);

                String memberName = member[0];
                int memberAge = Integer.parseInt(member[1]);
                boolean membershipStatus = Boolean.parseBoolean(member[2]);
                double price = Double.parseDouble(member[3]);
                boolean isPaid = Boolean.parseBoolean(member[4]);
                boolean inRestance = Boolean.parseBoolean(member[5]);

                Kontingent newKontingent = new Kontingent(memberName, memberAge, membershipStatus, price, isPaid, inRestance);
                newKontingent.setKontingentPrice();
                allKontingents.add(newKontingent);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return allKontingents;
    }






    /*
    //TODO SLET ALT UNDER og opret nye metoder
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

     */


}
