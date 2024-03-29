package com.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Project {
    private String id;
    private String title;
    private String description;
    private String imageUrl;
    private List<Skill>skills;
    private List<Bid>bids;
    private int budget;
    private long deadline;
    private User winner;

    public Project(){}

    public Project(String id, String title, String description, String imageUrl, List <Skill> skills, List <Bid> bids, int budget, long deadline, User winner) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
        this.skills = skills;
        this.bids = bids;
        this.budget = budget;
        this.deadline = deadline;
        this.winner = winner;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public List <Skill> getSkills() {
        return skills;
    }

    public void setSkills(List <Skill> skills) {
        this.skills = skills;
    }

    public List <Bid> getBids() {
        return bids;
    }

    public void setBids(List <Bid> bids) {
        this.bids = bids;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public long getDeadline() {
        return deadline;
    }

    public void setDeadline(long deadline) {
        this.deadline = deadline;
    }

    public User getWinner() {
        return winner;
    }

    public void setWinner(User winner) {
        this.winner = winner;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
