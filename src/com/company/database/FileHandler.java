package com.company.database;

import com.company.domain.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileHandler {
    private ArrayList<User> users = new ArrayList<>();


    //TODO måske merge user metoderne med members metoderne, da de begge gør stort set de samme ting, bare til forskellige arrayLister
    //--------------------------------------------------------------
    //USERS
    //--------------------------------------------------------------

    public void addUserToFile(User user) throws FileNotFoundException {
        PrintStream ps = new PrintStream(new FileOutputStream("UserList.txt", true));
        Scanner out = new Scanner("UserList.txt");


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

    public void clearFile (String fileName) {
        try {
            FileWriter fw = new FileWriter(fileName, false);
            fw.close();
        } catch (Exception e) {
            //TODO hvad skal der stå
            System.out.println("Can't clear file..");
        }
    }


}
