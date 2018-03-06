package com.org.bard.RecruitingAppDB.entities;

public class Account {

    public void setUid(long uid) {
        this.uid = uid;
    }

    public long uid;

    public String email;
    public String username;
    public String password;
    public String organization;

    public Account(String email, String username, String password, String organization) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.organization = organization;
    }

}
