package com.model;

import java.util.List;

public class User {
    private String id;
    private String firstName;
    private String lastName;
    private String jobTitle;
    private String profilePictureURL;
    private List<Skill> skills;
    private String bio;

    public User(){}

    public User(String id,
                String firstName,
                String lastName,
                String jobTitle,
                String profilePictureURL,
                List <Skill> skills,
                String bio) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.jobTitle = jobTitle;
        this.profilePictureURL = profilePictureURL;
        this.skills = skills;
        this.bio = bio;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getProfilePictureURL() {
        return profilePictureURL;
    }

    public void setProfilePictureURL(String profilePictureURL) {
        this.profilePictureURL = profilePictureURL;
    }

    public List <Skill> getSkills() {
        return skills;
    }

    public void setSkills(List <Skill> skills) {
        this.skills = skills;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public Boolean hasMinReq(String projectID) {
        int index = -1;
        for (int i = 0; i < Data.projects.size(); i++){
            if(Data.projects.get(i).getId().equals(projectID)){
                index = i;
                break;
            }
        }
        int hasThisSkill = 0;
        for (int i = 0; i < Data.projects.get(index).getSkills().size(); i++){
            hasThisSkill = 0;
            for (int j = 0; j < Data.user.getSkills().size(); j++){
                if(Data.user.getSkills().get(j).getName().equals(Data.projects.get(index).getSkills().get(i).getName())){
                    if(Data.user.getSkills().get(j).getPoint() < Data.projects.get(index).getSkills().get(i).getPoint()){
                        return false;
                    }
                    else{
                        hasThisSkill = 1;
                        break;
                    }
                }
            }
            if(hasThisSkill == 0){
                return false;
            }
        }
        return true;
    }

    public String printSkills() {
        StringBuilder result = new StringBuilder();
        for (int i=0; i<this.skills.size(); i++){
            result.append(this.skills.get(i).getName() + ":" + this.skills.get(i).getPoint() + " ");
        }

        return result.toString();
    }
}
