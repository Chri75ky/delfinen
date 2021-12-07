package com.company.domain.User;


public class User {

    private final String fullName;
    private final Role role;

    //konstruktør
    public User(String fullName, Role role) {
        this.fullName = fullName;
        this.role = role;
    }

    public String toCSV() {
        return  fullName + ";" +
                role;
    }

    //getters
    public String getFullName() {
        return fullName;
    }

    public Role getRole() {
        return role;
    }

    //toString
    @Override
    public String toString() {
        return "\n" + "Bruger: " + fullName + "\n" + "Rolle: " + role + "\n";
    }
}
