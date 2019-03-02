package com.model;

public class Endorse {
    public Endorse() {
    }

    public Endorse(String skill, String user1, String user2) {
        this.skill = skill;
        this.user1 = user1;
        this.user2 = user2;
    }

    private String skill;
    private String user1;
    private String user2;

    public String getUser2() {
        return user2;
    }

    public void setUser2(String user2) {
        this.user2 = user2;
    }

    public String getUser1() {
        return user1;
    }

    public void setUser1(String user1) {
        this.user1 = user1;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }
}
