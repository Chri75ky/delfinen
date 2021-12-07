package com.company.accounting;

import com.company.database.FileHandler;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class MembershipFee {
    FileHandler fh = new FileHandler();
    private static final String MEMBER_FILE = "data/Medlemmer.csv";
    private static final String KONTINGENT_FILE = "data/Kontingent.csv";


    public void chargeMember(String nameOfMember) {
        ArrayList<String[]> listOfMembers = fh.getLinesInFile(MEMBER_FILE);

        for (int i = 0; i < listOfMembers.size(); i++) {
            String[] memberData = listOfMembers.get(i);

            if (memberData[0].contentEquals(nameOfMember)) {
                makeKontingentForMember(memberData);
            }
        }
    }

    public void chargeAllMembers() {
        ArrayList<String[]> listOfMembers = fh.getLinesInFile(MEMBER_FILE);

        for (int i = 0; i < listOfMembers.size(); i++) {
            String[] memberData = listOfMembers.get(i);
            makeKontingentForMember(memberData);
        }
    }

    public void makeKontingentForMember(String[] memberData) {
        String memberName = memberData[0];
        int memberAge = Integer.parseInt(memberData[1]);
        boolean membershipStatus = Boolean.parseBoolean(memberData[2]);
        double price = 0;
        boolean isPaid = false;
        boolean inRestance = false;

        Kontingent newKontingent = new Kontingent(memberName, memberAge, membershipStatus, price, isPaid, inRestance);
        newKontingent.setKontingentPrice();
        String newKontingentCSV = newKontingent.toCSV();

        fh.saveToCSV(KONTINGENT_FILE, newKontingentCSV);
    }

    public int calculateExpectedKontingent() {
        double expectedKontingent = 0;

        ArrayList<String[]> listOfMembers = fh.getLinesInFile(MEMBER_FILE);

        for (int i = 0; i < listOfMembers.size(); i++) {
            String[] memberData = listOfMembers.get(i);

            String memberName = memberData[0];
            int memberAge = Integer.parseInt(memberData[1]);
            boolean membershipStatus = Boolean.parseBoolean(memberData[2]);

            Kontingent expKontingent = new Kontingent(memberName, memberAge, membershipStatus);
            expKontingent.setKontingentPrice();

            expectedKontingent += expKontingent.getPrice();


        }
        return (int) Math.round(expectedKontingent);
    }

    public void memberToRestance(String nameOfMember) {
        List<Kontingent> allKontingents = getAllKontingents();

        for (Kontingent kontigent : allKontingents) {
            if (kontigent.getMemberName().contentEquals(nameOfMember) && kontigent.eligibleForRestance()) {
                kontigent.setInRestance();
            }
        }
        updateKontigentFile(allKontingents);
    }

    public void allMembersToRestance() {
        List<Kontingent> allKontingents = getAllKontingents();

        for (Kontingent kontigent : allKontingents) {
            if (kontigent.eligibleForRestance()) {
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

        if (str.isEmpty()) {
            str.append("Der er ingen medlemmer i restance\n");
        }

        return str.toString();
    }

    public void deleteAllPaidKontingentsAndRestanceInFile() {
        List<Kontingent> allKontingents = getAllKontingents();
        List<Kontingent> allKontingentsWithoutPaid = new ArrayList<>();

        for (Kontingent kontigent : allKontingents) {
            if (!kontigent.getIsPaid()) {
                allKontingentsWithoutPaid.add(kontigent);
            }
        }
        updateKontigentFile(allKontingentsWithoutPaid);
    }

    public void updateKontigentFile(List<Kontingent> newData) {
        fh.clearFile(KONTINGENT_FILE);
        saveKontigentListToFile(newData);
    }

    public void saveKontigentListToFile(List<Kontingent> kontigents) {
        StringBuilder str = new StringBuilder();

        for (Kontingent k : kontigents) {
            String kontingentCSV = k.toCSV();
            str.append(kontingentCSV);
        }

        fh.saveToCSV(KONTINGENT_FILE, str.toString());
    }

    public List<Kontingent> getAllKontingents() {
        ArrayList<String[]> listOfKontingents = fh.getLinesInFile(KONTINGENT_FILE);
        List<Kontingent> allKontingents = new ArrayList<>();

        for (int i = 0; i < listOfKontingents.size(); i++) {
            String[] kontingentData = listOfKontingents.get(i);

            String memberName = kontingentData[0];

            int memberAge = Integer.parseInt(kontingentData[1]);

            boolean membershipStatus;
            if (kontingentData[2].contentEquals("Aktivt medlemsskab")) {
                membershipStatus = true;
            } else {
                membershipStatus = false;
            }

            double price = Double.parseDouble(kontingentData[3]);

            boolean isPaid;
            if (kontingentData[4].contentEquals("Betalt")) {
                isPaid = true;
            } else {
                isPaid = false;
            }

            boolean inRestance;

            if (kontingentData[5].contentEquals("Restance")) {
                inRestance = true;
            } else {
                inRestance = false;
            }

            Kontingent newKontingent = new Kontingent(memberName, memberAge, membershipStatus, price, isPaid, inRestance);
            newKontingent.setKontingentPrice();
            allKontingents.add(newKontingent);

        }
        return allKontingents;
    }

    public boolean checkMemberFileForMembers() {
        boolean membersInFile = fh.checkFileForLines(MEMBER_FILE);
        return membersInFile;
    }

    public boolean memberExistInFile(String nameOfMember, String fileName) {
        String filePath = new String();

        if (fileName.contains("mF")) {
            filePath = MEMBER_FILE;
        } else if (fileName.contains("kF")) {
            filePath = KONTINGENT_FILE;
        }

        boolean memberExists = fh.checkFileForName(filePath, nameOfMember);
        return memberExists;
    }

    public boolean checkKontingentsForPaid() {
        List<Kontingent> allKontingents = getAllKontingents();
        boolean aKontigentIsPaid = false;

        for (Kontingent kontigent : allKontingents) {
            if (kontigent.getIsPaid() == true) {
                aKontigentIsPaid = true;
            }
        }
        return aKontigentIsPaid;
    }

    public String showListOfMembers(String fileName) {
        String filePath = new String();

        if (fileName.contains("mF")) {
            filePath = MEMBER_FILE;
        } else if (fileName.contains("kF")) {
            filePath = KONTINGENT_FILE;
        }
        String namesInFile = fh.getNamesInFile(filePath);
        return namesInFile;
    }

    public boolean memberExistsAndOwes(String nameOfMember) {
        boolean memberExistsAndOwes = false;
        List<Kontingent> allKontingents = getAllKontingents();

        for (Kontingent kontigent : allKontingents) {
            if (kontigent.getMemberName().contentEquals(nameOfMember) && kontigent.getIsPaid() == false) {
                memberExistsAndOwes = true;
            }
        }

        return memberExistsAndOwes;
    }

    public String billsForMemberThatOwes (String nameOfMember) {
        List<Kontingent> allKontingents = getAllKontingents();
        StringBuilder str = new StringBuilder();

        str.append(nameOfMember + " har følgende manglende betalinger:\n");
        String restanceOrNot = "";
        int count = 1;
        for (Kontingent k : allKontingents) {
            if (k.getMemberName().contentEquals(nameOfMember) && !k.getIsPaid()) {

                if (k.getInRestance() == false) {
                    restanceOrNot = "i kontigent";
                } else {
                    restanceOrNot = "i restance";
                }

                str.append("(" + count + ") " + k.getPrice() + " DKK " + restanceOrNot + "\n");
                count++;
            }
        }


        return str.toString();
    }

    public void payAllBills(String nameOfMember) {
        List<Kontingent> allKontingents = getAllKontingents();

        for (Kontingent k : allKontingents) {
            if (k.getMemberName().contentEquals(nameOfMember) && k.getIsPaid() == false) {
                k.isPaid();
            }
        }
        updateKontigentFile(allKontingents);
    }

}
