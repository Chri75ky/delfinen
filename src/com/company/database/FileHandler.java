package com.company.database;

import com.company.domain.Member;
import com.company.domain.User;

import java.util.ArrayList;

public class FileHandler {
    ArrayList<Member> members = new ArrayList<>();
    ArrayList<User> users = new ArrayList<>();


    //--------------------------------------------------------------
    //MEMBERS
    //--------------------------------------------------------------
    //tilføjer medlem til arrayList
    public void saveMember(Member member) {
        members.add(member);
    }

    //printer arrayList ud
    public String seeMembers() {
        return members.toString();
    }

    //finder et medlem i listen ved brug af navnet, returnerer et medlem
    public Member findMember(String memberName) {
        for (int i = 0; i < members.size(); i++) {
            if (memberName.equalsIgnoreCase(members.get(i).getFullName())) {
                return members.get(i);
            } else return null;
        }
        return null;
    }

    //sletter medlem fra arrayList members
    public void deleteMember(Member member) {
        members.remove(member);
    }

    //--------------------------------------------------------------
    //USERS
    //--------------------------------------------------------------

    //tilføjer en bruger til arrayListen users
    public void saveUser(User user) {
        users.add(user);
    }

    //sletter user fra arrayList users
    public void deleteUser(User user) {
        users.remove(user);
    }

    //finder en bruger i brugerListen, ved brug af navn
    public User findUser(String userName) {
        for (int i = 0; i < users.size(); i++) {
            if (userName.equalsIgnoreCase(users.get(i).getFullName())) {
                return users.get(i);
            } else return null;
        }
        return null;
    }

    //printer arrayList ud
    public String seeUsers() {
        return users.toString();
    }
}
