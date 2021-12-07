package com.company.accounting;

import com.company.database.FileHandler;

import java.util.ArrayList;
import java.util.List;


public class MembershipFee {
    FileHandler fh = new FileHandler();
    private static final String MEMBER_FILE = "data/Medlemmer.csv";
    private static final String KONTINGENT_FILE = "data/Kontingent.csv";


    public void chargeMember(String nameOfMember) {
        ArrayList<String[]> listOfMembers = fh.getLinesInFile(MEMBER_FILE);

        for (String[] memberData : listOfMembers) {
            if (memberData[0].contentEquals(nameOfMember)) {
                makeKontingentForMember(memberData);
            }
        }
    }

    public void chargeAllMembers() {
        ArrayList<String[]> listOfMembers = fh.getLinesInFile(MEMBER_FILE);

        for (String[] memberData : listOfMembers) {
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

    public int amountOfMembersInFile() {
        ArrayList<String[]> listOfMembers = fh.getLinesInFile(MEMBER_FILE);
        return listOfMembers.size();
    }

    public int calculateExpectedKontingent() {
        double expectedKontingent = 0;

        ArrayList<String[]> listOfMembers = fh.getLinesInFile(MEMBER_FILE);

        for (String[] memberData : listOfMembers) {
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

        for (Kontingent kontingent : allKontingents) {
            if (kontingent.getMemberName().contentEquals(nameOfMember) && kontingent.eligibleForRestance()) {
                kontingent.setInRestance();
            }
        }
        updateKontingentFile(allKontingents);
    }

    public void allMembersToRestance() {
        List<Kontingent> allKontingents = getAllKontingents();

        for (Kontingent kontingent : allKontingents) {
            if (kontingent.eligibleForRestance()) {
                kontingent.setInRestance();
            }
        }
        updateKontingentFile(allKontingents);
    }

    public String getMembersInRestance() {
        List<Kontingent> allKontingents = getAllKontingents();
        StringBuilder str = new StringBuilder();

        int index = 1;
        for (Kontingent kontingent : allKontingents) {
            if (kontingent.getInRestance() && !kontingent.getIsPaid()) {
                str.append("(" + index + ") " + kontingent.getMemberName() + " har " + kontingent.getPrice() + " DKK i restance\n");
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

        for (Kontingent kontingent : allKontingents) {
            if (!kontingent.getIsPaid()) {
                allKontingentsWithoutPaid.add(kontingent);
            }
        }
        updateKontingentFile(allKontingentsWithoutPaid);
    }

    public void updateKontingentFile(List<Kontingent> kontingentsForFile) {
        fh.clearFile(KONTINGENT_FILE);
        saveKontingentListToFile(kontingentsForFile);
    }

    public void saveKontingentListToFile(List<Kontingent> kontingents) {
        StringBuilder kontingentsForFile = new StringBuilder();

        for (Kontingent k : kontingents) {
            String kontingentCSV = k.toCSV();
            kontingentsForFile.append(kontingentCSV);
        }

        fh.saveToCSV(KONTINGENT_FILE, kontingentsForFile.toString());
    }

    public List<Kontingent> getAllKontingents() {
        ArrayList<String[]> listOfKontingents = fh.getLinesInFile(KONTINGENT_FILE);
        List<Kontingent> allKontingents = new ArrayList<>();

        for (String[] kontingentData : listOfKontingents) {
            String memberName = kontingentData[0];

            int memberAge = Integer.parseInt(kontingentData[1]);

            boolean membershipStatus;
            if (kontingentData[2].contentEquals("Aktivt medlemskab")) {
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
        return fh.checkFileForLines(MEMBER_FILE);
    }

    public boolean memberExistInFile(String nameOfMember, String fileName) {
        String filePath = "";

        if (fileName.contains("mF")) {
            filePath = MEMBER_FILE;
        } else if (fileName.contains("kF")) {
            filePath = KONTINGENT_FILE;
        }

        return fh.checkFileForName(filePath, nameOfMember);
    }

    public boolean checkKontingentsForPaid() {
        List<Kontingent> allKontingents = getAllKontingents();
        boolean aKontingentIsPaid = false;

        for (Kontingent kontingent : allKontingents) {
            if (kontingent.getIsPaid()) {
                aKontingentIsPaid = true;
                break;
            }
        }
        return aKontingentIsPaid;
    }

    public String showListOfMembers(String fileName) {
        String filePath = "";

        if (fileName.contains("mF")) {
            filePath = MEMBER_FILE;
        } else if (fileName.contains("kF")) {
            filePath = KONTINGENT_FILE;
        }
        return fh.getNamesInFile(filePath);
    }

    public boolean memberExistsAndOwes(String nameOfMember) {
        boolean memberExistsAndOwes = false;
        List<Kontingent> allKontingents = getAllKontingents();

        for (Kontingent kontingent : allKontingents) {
            if (kontingent.getMemberName().contentEquals(nameOfMember) && !kontingent.getIsPaid()) {
                memberExistsAndOwes = true;
            }
        }

        return memberExistsAndOwes;
    }

    public String billsForMemberThatOwes (String nameOfMember) {
        List<Kontingent> allKontingents = getAllKontingents();
        StringBuilder str = new StringBuilder();

        str.append(nameOfMember + " har følgende manglende betalinger:\n");
        String restanceOrNot;
        int count = 1;
        for (Kontingent k : allKontingents) {
            if (k.getMemberName().contentEquals(nameOfMember) && !k.getIsPaid()) {

                if (!k.getInRestance()) {
                    restanceOrNot = "i kontingent";
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
            if (k.getMemberName().contentEquals(nameOfMember) && !k.getIsPaid()) {
                k.isPaid();
            }
        }
        updateKontingentFile(allKontingents);
    }

}
