package com.company.accounting;

import com.company.database.FileHandler;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


//TODO evt. nogen af metoderne (især dem der return boolean) kan måske sammensættes hvis man laver et par ændringer så der kommer mindre kode
//TODO evt. lav en/flere sort metoder der laver list af members i alfabetisk rækkefølge/hvem der skylder mest


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

        saveToCSV(KONTINGENT_FILE, newKontingentCSV);
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





    //TODO make boolean check if kontigent aligebel to set in restance in 'Kontingent class'
    public void memberToRestance(String nameOfMember) {
        List<Kontingent> allKontingents = getAllKontingents();

        for (Kontingent kontigent : allKontingents) {
            if (kontigent.getInRestance() == false && kontigent.getMemberName().contentEquals(nameOfMember) && kontigent.getIsPaid() == false) {
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
    //-------------

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
            if (kontigent.getIsPaid() != true) {
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
        for (Kontingent k : kontigents) {
            String kontingentCSV = k.toCSV();
            saveToCSV(KONTINGENT_FILE, kontingentCSV);
        }
    }

    //TODO Kan måske rykkes til filehandler
    private void saveToCSV(String file, String csvLine) {
        try {
            PrintStream ps = new PrintStream(new FileOutputStream(file, true));
            ps.append(csvLine).append("\n");
            ps.close();
        } catch (FileNotFoundException e) {
            System.out.println("The file does not exist");
        }
    }

    //TODO kan måske rykkes til filehandler
    public List<Kontingent> getAllKontingents() {
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
                boolean membershipStatus;
                if (member[2].contentEquals("Aktivt medlemsskab")) {
                    membershipStatus = true;
                } else {
                    membershipStatus = false;
                }

                double price = Double.parseDouble(member[3]);
                boolean isPaid;
                if (member[4].contentEquals("Betalt")) {
                    isPaid = true;
                } else {
                    isPaid = false;
                }

                boolean inRestance;
                if (member[5].contentEquals("Restance")) {
                    inRestance = true;
                } else {
                    inRestance = false;
                }

                Kontingent newKontingent = new Kontingent(memberName, memberAge, membershipStatus, price, isPaid, inRestance);
                newKontingent.setKontingentPrice();
                allKontingents.add(newKontingent);
            }
            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return allKontingents;
    }

    //TODO kan rykkes til filehandler og måske sammensættes
    public boolean checkMemberFileForMembers() {
        boolean membersInFile = false;

        String line = "";
        String splitBy = ";";

        try {
            BufferedReader br = new BufferedReader(new FileReader(MEMBER_FILE));
            if ((line = br.readLine()) != null) {
                membersInFile = true;
            }
            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return membersInFile;
    }

    public boolean memberExistInFile(String nameOfMember, String fileName) {
        boolean memberExists = false;
        String file = new String();

        if (fileName.contains("mF")) {
            file = MEMBER_FILE;
        } else if (fileName.contains("kF")) {
            file = KONTINGENT_FILE;
        }

        String line = "";
        String splitBy = ";";

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));

            while ((line = br.readLine()) != null) {   //Returns a Boolean value
                String[] member = line.split(splitBy);

                if (member[0].contentEquals(nameOfMember)) {
                    memberExists = true;
                }
            }
            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return  memberExists;
    }
    //---------

    public boolean checkKontingentsForPaid() {
        List<Kontingent> allKontingents = getAllKontingents();
        boolean kontigentPaid = false;

        for (Kontingent kontigent : allKontingents) {
            if (kontigent.getIsPaid() == true) {
                kontigentPaid = true;
            }
        }
        return kontigentPaid;
    }

    //TODO kan rykkes til filehandler
    public String showListOfMembers(String fileName) {
        String file = new String();
        StringBuilder str = new StringBuilder();

        if (fileName.contains("mF")) {
            file = MEMBER_FILE;
        } else if (fileName.contains("kF")) {
            file = KONTINGENT_FILE;
        }

        int count = 1;
        String line = "";
        String splitBy = ";";

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            if ((line = br.readLine()) != null) {
                String[] member = line.split(splitBy);
                str.append("(" + count + ") " + member[0] + "\n");
                count++;

            }
            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return str.toString();
    }

    public boolean memberExistsAndOwes(String nameOfMember) {
        boolean memberExists = false;
        List<Kontingent> allKontingents = getAllKontingents();

        for (Kontingent kontigent : allKontingents) {
            if (kontigent.getMemberName().contentEquals(nameOfMember) && kontigent.getIsPaid() == false) {
                memberExists = true;
            }
        }

        return memberExists;
    }

    public String billsForMemberThatOwes (String nameOfMember) {
        List<Kontingent> allKontingents = getAllKontingents();
        StringBuilder str = new StringBuilder();

        str.append(nameOfMember + " har følgende manglende betalinger:\n");
        String restanceOrNot = new String();
        int count = 1;
        for (Kontingent k : allKontingents) {
            if (k.getMemberName().contentEquals(nameOfMember)) {

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
