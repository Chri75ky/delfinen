package com.company.domain.Member;

import com.company.domain.Competition;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class MemberController {
    private final ArrayList<Member> members = new ArrayList<>();
    private final ArrayList<CompSwimmer> compSwimmers = new ArrayList<>();
    private final ArrayList<Competition> competitions = new ArrayList<>();
    private final ArrayList<CompSwimmer> topFiveCompSwimmers = new ArrayList<>();

    // Tilføjer et medlem til en liste ude fra programmet
    public void addMembersToFile() throws FileNotFoundException {
        PrintStream ps = new PrintStream(new FileOutputStream("data/Medlemmer.csv", false));
        Scanner out = new Scanner("data/Medlemmer.csv");

        while (out.hasNextLine()) {
            out.nextLine();
        }

        // Tilføjer medlemmet til filen
        ps.append(memberToCSV());

    }

    public void addCompSwimmerToFile() throws FileNotFoundException {
        PrintStream ps = new PrintStream(new FileOutputStream("data/KonkurrenceSvømmer.csv", false));
        Scanner out = new Scanner("data/KonkurrenceSvømmer.csv");

        while (out.hasNextLine()) {
            out.nextLine();
        }

        // Tilføjer konkurrence svømmeren til filen
        ps.append(compSwimmerToCSV());

    }

    //gemmer en member til arrayListen af members
    public void saveMember(Member member) {
        members.add(member);
    }

    //gemmer en konkurrencesvømmer til dets arrayList
    public void saveCompSwimmer(CompSwimmer compSwimmer) {
        compSwimmers.add(compSwimmer);
    }

    // Finder og returner et medlem fra ArrayList
    public Member findMember(String name) {
        for (Member member : members) {
            if (member.getFullName().equals(name)) {
                return member;
            }
        }
        return null;
    }

    // Laver en CSV formateret String af ArrayList af medlemmer
    public String memberToCSV() {
        StringBuilder sb = new StringBuilder();

        for (Member member: members) {
            sb.append(member.toCSV()).append('\n');
        }
        return sb.toString();
    }

    // Samme som til member ArrayList
    public String compSwimmerToCSV() {
        StringBuilder sb = new StringBuilder();

        for (CompSwimmer compSwimmer : compSwimmers) {
            sb.append(compSwimmer.toCSV()).append('\n');
        }

        return sb.toString();
    }

    // Læser medlemmer fra deres fil og viser til bruger
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

    // Samme som medlemmer
    //Burde nok ha' fundet en måde at sammensætte alle de her gentagende metoder, med brug af et interface?
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

    /* Del af kode fundet her : https://stackoverflow.com/questions/10960213/how-can-i-read-comma-separated-values-from-a-text-file-in-java
     Når medlemmer bliver indlæst fra fil bliver de også skabt som Member objekter igen og sat ind i arrayListen.
     Så nu burde det være muligt at kunne arbejde med members selv efter et reboot af programmet. Så længe at
      metoden er blevet kørt.
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
                double bestTime = Double.parseDouble(tokens[5]);
                CompSwimmer currentCompSwimmer = new CompSwimmer(name, age, membershipStatus, discipline, bestTime);
                compSwimmers.add(currentCompSwimmer);
            }
        } catch (IOException e) {
            System.out.println("File Read Error");
        }
    }

    //finder det ønskede konkurrencesvømmer i arraylisten
    public CompSwimmer findCompSwimmer(String name) {
        for (CompSwimmer compSwimmer : compSwimmers) {
            if (compSwimmer.getFullName().equals(name)) {
                return compSwimmer;
            }
        }
        return null;
    }

    // Sætter en svømmers tid og tilføjer dem til den anden ArrayListe så de bliver tilføjet til fil, med den nye tid
    public void setCompSwimmerStats(CompSwimmer compSwimmer, double time) {
        compSwimmer.setTime(time);
        compSwimmers.remove(compSwimmer);
        compSwimmers.add(compSwimmer);
    }

    // Viser de forskellige tider for svømmere
    public void showCompSwimmerTimes() {
        for (CompSwimmer compSwimmer : compSwimmers) {
            if (compSwimmer.getTime() != 0) {
                System.out.println(compSwimmer.getFullName() + " " + compSwimmer.getTime() + " sek. " + compSwimmer.getDiscipline());
            } else {
                System.out.println(compSwimmer.getFullName() + " har ingen registreret tid!");
            }

        }

    }

    //gemmer konkurrence til dets svarende ArrayList
    public void addCompetition(Competition competition) {
        competitions.add(competition);
    }

    // Viser alle de eksisterende konkurrencer
    public String showCompetitions() {
        if (competitions.size() != 0) {
            return competitions.toString();
        } else {
            return null;
        }
    }

    //denne metode sammenligner? sorterer? svømmerne fra arraylisten og putter de bedste tider ind i arayet
    //UWU
    public void getTopFiveList() {
        QuickSort.sort(compSwimmers);
        CompSwimmer firstPlace = compSwimmers.get(0);
        CompSwimmer secondPlace = compSwimmers.get(1);
        CompSwimmer thirdPlace = compSwimmers.get(2);
        CompSwimmer fourthPlace = compSwimmers.get(3);
        CompSwimmer fifthPlace = compSwimmers.get(4);

        topFiveCompSwimmers.add(firstPlace);
        topFiveCompSwimmers.add(secondPlace);
        topFiveCompSwimmers.add(thirdPlace);
        topFiveCompSwimmers.add(fourthPlace);
        topFiveCompSwimmers.add(fifthPlace);

    }

    //skriver listen af top 5 ud, ind i dets svarende CSV fil
    public void addTopFiveToFile() throws FileNotFoundException {
        getTopFiveList();
        PrintStream ps = new PrintStream(new FileOutputStream("data/Top Five.csv", false));
        Scanner out = new Scanner("data/Top Five.csv");

        while (out.hasNextLine()) {
            out.nextLine();
        }

        // Tilføjer medlemmet til filen
        ps.append(topFiveToCSV());
    }

    //CSV
    public String topFiveToCSV() {
        StringBuilder sb = new StringBuilder();

        for (CompSwimmer compSwimmer : topFiveCompSwimmers) {
            sb.append(compSwimmer.toCSV()).append('\n');
        }

        return sb.toString();
    }

    //indlæser listen fra filen
    public void readTopFiveFromFile() {
        try (BufferedReader in = new BufferedReader(new FileReader("data/Top Five.csv"))) {
            String str;
            while ((str = in.readLine()) != null) {
                String[] tokens = str.split(";");
                System.out.println(Arrays.toString(tokens));
            }
        } catch (IOException e) {
            System.out.println("File Read Error");
        }
    }

    //fjerner et medlem fra ArrayListen
    public void removeMember(Member member) {
        members.remove(member);
    }
}
