package com.company.domain.User;


import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class UserController {
    private final ArrayList<User> users = new ArrayList<>();


    //tilføjer en bruger til arrayListen users
    public void saveUser(User user) throws FileNotFoundException {
        users.add(user);
        addUserToFile(user);
    }

    //finder en bruger i brugerListen, ved brug af navn
    public User findUser(String name) {
        for (com.company.domain.User.User user : users) {
            if (user.getFullName().equals(name)) {
                return user;
            }
        }
        return null;
    }

    /*bruges ikke, da man ikke skal kunne se brugerne i programmet,
    men har lavet metoden just in case, mere for at se
    om indlæsning til og fra filen fungerer som det skal*/
    public StringBuilder showUsersFromFile() throws FileNotFoundException {
        Scanner users = new Scanner(new File("data/Brugere.csv"));
        StringBuilder allUsers = new StringBuilder();

        while (users.hasNextLine()) {
            allUsers.append(users.nextLine() + "\n");
        }
        return allUsers;
    }


    public void addUserToFile(User user) throws FileNotFoundException {
        PrintStream ps = new PrintStream(new FileOutputStream("data/Brugere.csv", true));
        Scanner out = new Scanner("data/Brugere.csv");

        while (out.hasNextLine()) {
            out.nextLine();
        }

        ps.append(toCSV());
    }

    public String toCSV() {
        StringBuilder sb = new StringBuilder();

        for (User user : users) {
            sb.append(user.toCSV()).append('\n');
        }

        return sb.toString();
    }

    //sletter user fra arrayList users
    public void deleteUser(User user) {
        users.remove(user);
    }

    //TODO lav at den henter users fra filen
    //printer arrayList ud
    public String seeUsers() {
        return users.toString();
    }

    public void loadUsersFromFile() {
        try (BufferedReader in = new BufferedReader(new FileReader("data/Brugere.csv"))) {
            String str;
            while ((str = in.readLine()) != null) {
                String[] tokens = str.split(";");
                String name = tokens[0];
                Role role = Role.valueOf(tokens[1]);

                User currentUser = new User(name, role);
                users.add(currentUser);
            }
        } catch (IOException e) {
            System.out.println("File Read Error");
        }
    }
}
