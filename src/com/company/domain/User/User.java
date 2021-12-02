package com.company.domain.User;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class User {

    private String fullName;
    private Role role;


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
