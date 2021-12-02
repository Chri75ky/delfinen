package com.company.domain.User;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class User {

    private String fullName;
    private Role role;
    private ArrayList<User> users = new ArrayList<>();



    //CSV konstruktør
    public User(String CSV) {
        String[] elements = CSV.split(";");

        this.fullName = elements[0];
        this.role = Role.valueOf(elements[1]);

    }

    public String toCSV() {
        return  fullName + ";" +
                role;
    }

    //---------------------------------
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

    //tilføjer en bruger til arrayListen users
    public void saveUser(User user) throws FileNotFoundException {
        users.add(user);
        addUserToFile(user);
    }

    //sletter user fra arrayList users
    public void deleteUser(User user) {
        users.remove(user);
    }


    //TODO INPROGRESS prøver at lave sådan at den kan finde brugeren direkte fra textfilen
    //finder en bruger i brugerListen, ved brug af navn
    public User findUser(String userName) throws IOException {
       /* for (int i = 0; i < users.size(); i++) {
            if (userName.equalsIgnoreCase(users.get(i).getFullName())) {
                return users.get(i);
            } else return null;
        }
        return null;*/

        return null;
    }

    //TODO lav at den henter users fra filen
    //printer arrayList ud
    public String seeUsers() {
        return users.toString();
    }

    public StringBuilder showUsersFromFile() throws FileNotFoundException {
        Scanner users = new Scanner(new File("UserList.txt"));
        StringBuilder allUsers = new StringBuilder();

        while (users.hasNextLine()) {
            allUsers.append(users.nextLine() + "\n");
        }
        return allUsers;
    }

    //getters
    public String getFullName() {
        return fullName;
    }

    public User(String fullName, Role role) {
        this.fullName = fullName;
        this.role = role;
    }

    public Role getRole() {
        return role;
    }

    @Override
    public String toString() {
        return "\n" + "Bruger: " + fullName + "\n" + "Rolle: " + role + "\n";
    }
}
