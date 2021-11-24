package com.company.database;

import com.company.domain.Member;

import java.util.ArrayList;

public class FileHandler {
    ArrayList<Member> members = new ArrayList<>();

    public void saveMember(Member member) {
        members.add(member);
    }

    public String seeMembers() {
        return members.toString();
    }
}
