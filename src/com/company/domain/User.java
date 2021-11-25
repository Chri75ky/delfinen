package com.company.domain;

public class User {

    private String fullName;
    private Role role;

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

    //rollen af brugeren
    enum Role {
        FORMAND,
        KASSERER,
        TRÆNER;
    }

    @Override
    public String toString() {
        return "\n" + "Bruger: " + fullName + "\n" + "Rolle: " + role + "\n";
    }
}
