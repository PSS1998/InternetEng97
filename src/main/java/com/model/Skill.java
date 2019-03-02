package com.model;

public class Skill {
    private String name;
    private int point;

    public Skill(){}

    public Skill(String name, int point) {
        this.name = name;
        this.point = point;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public void endorse() {
        this.point = this.point+1;
    }
}
