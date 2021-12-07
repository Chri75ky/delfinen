package com.company.domain.Member;

import com.company.domain.Competition;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class MemberController {
    private final ArrayList<Member> members = new ArrayList<>();
    private final ArrayList<Member> newMembers = new ArrayList<>();
    private final ArrayList<CompSwimmer> compSwimmers = new ArrayList<>();
    private final ArrayList<CompSwimmer> newCompSwimmers = new ArrayList<>();
    private final ArrayList<Competition> competitions = new ArrayList<>();
    private final ArrayList<Double> bestTimes = new ArrayList<>();

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
        newMembers.add(member);
    }

    public void saveCompSwimmer(CompSwimmer compSwimmer) {
        newCompSwimmers.add(compSwimmer);
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

        for (Member member : newMembers) {
            sb.append(member.toCSV()).append('\n');
        }

        return sb.toString();
    }

    public String compSwimmerToCSV() {
        StringBuilder sb = new StringBuilder();

        for (CompSwimmer compSwimmer : newCompSwimmers) {
            sb.append(compSwimmer.toCSV()).append('\n');
        }

        return sb.toString();
    }

    public void readMembersFromFile() {
        try (BufferedReader in = new BufferedReader(new FileReader("data/Medlemmer.csv"))) {
            String str;
            while ((str = in.readLine()) != null) {
                String[] tokens = str.split(";");
                System.out.println(Arrays.toString(tokens));
            }
        } catch (IOException e) {
            System.out.println("File Read Error");
        }
    }

    public void readCompSwimmersFromFile() {
        try (BufferedReader in = new BufferedReader(new FileReader("data/KonkurrenceSvømmer.csv"))) {
            String str;
            while ((str = in.readLine()) != null) {
                String[] tokens = str.split(";");
                System.out.println(Arrays.toString(tokens));
            }
        } catch (IOException e) {
            System.out.println("File Read Error");
        }
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
                String name = tokens[0];
                int age = Integer.parseInt(tokens[1]);
                boolean membershipStatus;
                membershipStatus = tokens[2].equalsIgnoreCase("true");

                Member currentMember = new Member(name, age, membershipStatus);
                members.add(currentMember);
            }
        } catch (IOException e) {
            System.out.println("File Read Error");
        }
    }

    // Samme som metoden ovenover
    public void loadCompSwimmerFromFile() {
        try (BufferedReader in = new BufferedReader(new FileReader("data/KonkurrenceSvømmer.csv"))) {
            String str;
            while ((str = in.readLine()) != null) {
                String[] tokens = str.split(";");
                String name = tokens[0];
                int age = Integer.parseInt(tokens[1]);
                boolean membershipStatus;
                membershipStatus = tokens[2].equalsIgnoreCase("true");
                Disciplin discipline = Disciplin.valueOf(tokens[4]);
                if (tokens.length < 5) {
                    double bestTime = Double.parseDouble(tokens[5]);
                    CompSwimmer currentCompSwimmer = new CompSwimmer(name, age, membershipStatus, discipline, bestTime);
                } else {
                    CompSwimmer currentCompSwimmer = new CompSwimmer(name, age, membershipStatus, discipline);
                    compSwimmers.add(currentCompSwimmer);
                }
            }
        } catch (IOException e) {
            System.out.println("File Read Error");
        }
    }

    public CompSwimmer findCompSwimmer(String name) {
        for (CompSwimmer compSwimmer : compSwimmers) {
            if (compSwimmer.getFullName().equals(name)) {
                return compSwimmer;
            }
        }
        return null;
    }

    public void setCompSwimmerStats(CompSwimmer compSwimmer, double time) {
        compSwimmer.setBestTime(time);
        if(bestTimes.size() < 5) {
            bestTimes.add(time);
        }
    }

    public void showCompSwimmerTimes() {
        for (CompSwimmer compSwimmer : compSwimmers) {
            if (compSwimmer.getBestTime() != 0) {
                System.out.println(compSwimmer.getFullName() + " " + compSwimmer.getBestTime() + " sek. " + compSwimmer.getDiscipline());
            } else {
                System.out.println(compSwimmer.getFullName() + " har ingen registreret tid!");
            }

        }

    }

    public void addCompetition(Competition competition) {
        competitions.add(competition);
    }
    public String showCompetitions() {
        if (competitions.size() != 0) {
            return competitions.toString();
        } else {
            return null;
        }
    }

    public String getTopFive() {
       return bestTimes.toString();
    }
}
