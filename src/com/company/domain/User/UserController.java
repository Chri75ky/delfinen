package com.company.domain.User;

import com.company.domain.Member.Member;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class UserController {
    private ArrayList<User> users = new ArrayList<>();


    //tilføjer en bruger til arrayListen users
    public void saveUser(User user) throws FileNotFoundException {
        users.add(user);
        addUserToFile(user);
    }

    //TODO INPROGRESS prøver at lave sådan at den kan finde brugeren direkte fra textfilen
    //finder en bruger i brugerListen, ved brug af navn
    public User findUser(String name) {
        for (com.company.domain.User.User user : users) {
            if (user.getFullName().equals(name)) {
                return user;
            }
        }
        return null;
    }

    public StringBuilder showUsersFromFile() throws FileNotFoundException {
        Scanner users = new Scanner(new File("UserList.txt"));
        StringBuilder allUsers = new StringBuilder();

        while (users.hasNextLine()) {
            allUsers.append(users.nextLine() + "\n");
        }
        return allUsers;
    }

    public void addUserToFile(User user) throws FileNotFoundException {
        PrintStream ps = new PrintStream(new FileOutputStream("Brugere.csv", true));
        Scanner out = new Scanner("Brugere.csv");


        while (out.hasNextLine()) {
            out.nextLine();
        }

        // Tilføjer user til "UserList.txt"
        ps.append("\n" + "Navn: " + user.getFullName() + "\n" + "Rolle: " + user.getRole() + "\n");

        //TODO Fjern user fra arraylist?
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
}
