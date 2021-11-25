package com.company.database;

import com.company.domain.Member;

import java.util.ArrayList;

public class FileHandler {
    ArrayList<Member> members = new ArrayList<>();

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

    //sletter medlem fra arrayList
    public void deleteMember(Member member) {
        members.remove(member);
    }
}
