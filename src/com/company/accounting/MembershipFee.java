package com.company.accounting;
import com.company.database.FileHandler;
import java.io.*;
import java.util.ArrayList;
import java.util.List;


//TODO kig på om try catch - catch er rigtig eller om der er noget andet der skal skrives ud
//TODO update den måde filen står på, så der ikke står true/false, men istedet fx paid/not paid og in restance/not in restance + opdeling bedre med kollonner og overskrifter på første row
//TODO måske også lav en metode der laver list af members i alfabetisk rækkefølge


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
        updateKontigentFile(allKontingents);
    }

    public void allMembersToRestance() {
        List<Kontingent> allKontingents = getAllKontingents();

        for (Kontingent kontigent : allKontingents) {
            if (kontigent.getInRestance() == false && kontigent.getIsPaid() == false) {
                kontigent.setInRestance();
            }
        }
        updateKontigentFile(allKontingents);
    }

    public String getMembersInRestance() {
        List<Kontingent> allKontigents = getAllKontingents();
        StringBuilder str = new StringBuilder();

        int index = 1;
        for (Kontingent kontigent : allKontigents) {
            if (kontigent.getInRestance() == true && kontigent.getIsPaid() == false) {
                str.append("(" + index + ") " + kontigent.getMemberName() + " har " + kontigent.getPrice() + " DKK i restance\n");
                index++;
            }
        }
        return str.toString();
    }

    public void updateKontigentFile(List<Kontingent> newData) {
        fh.clearFile(KONTINGENT_FILE);
        saveKontigentListToFile(newData);
    }

    public void saveKontigentListToFile(List<Kontingent> kontigents) {
        for (Kontingent k : kontigents) {
            String kontingentCSV = k.toCSV();
            saveToCSV(KONTINGENT_FILE, kontingentCSV);
        }
    }

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

}
