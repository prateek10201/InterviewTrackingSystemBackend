package com.backend.ITS03.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class UserProfile implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private long id;
    private String userId;
    private String candidateId;
    private String firstName;
    private String lastName;
    private String primarySkillSet;
    private String secondarySkillSet;
    private String experience;
    private String designation;
    private String joiningPeriod;
    private String location;
    private boolean interviewStatus;
    private String lastInterview;

    public UserProfile() {
    }

    public UserProfile(String userId, String candidateId, String firstName, String lastName, String primarySkillSet, String secondarySkillSet, String experience, String designation, String joiningPeriod, String location, boolean interviewStatus, String lastInterview) {
        this.userId = userId;
        this.candidateId = candidateId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.primarySkillSet = primarySkillSet;
        this.secondarySkillSet = secondarySkillSet;
        this.experience = experience;
        this.designation = designation;
        this.joiningPeriod = joiningPeriod;
        this.location = location;
        this.interviewStatus = interviewStatus;
        this.lastInterview = lastInterview;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(String candidateId) {
        this.candidateId = candidateId;
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

    public String getPrimarySkillSet() {
        return primarySkillSet;
    }

    public void setPrimarySkillSet(String primarySkillSet) {
        this.primarySkillSet = primarySkillSet;
    }

    public String getSecondarySkillSet() {
        return secondarySkillSet;
    }

    public void setSecondarySkillSet(String secondarySkillSet) {
        this.secondarySkillSet = secondarySkillSet;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getJoiningPeriod() {
        return joiningPeriod;
    }

    public void setJoiningPeriod(String joiningPeriod) {
        this.joiningPeriod = joiningPeriod;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public boolean isInterviewStatus() {
        return interviewStatus;
    }

    public void setInterviewStatus(boolean interviewStatus) {
        this.interviewStatus = interviewStatus;
    }

    public String getLastInterview() {
        return lastInterview;
    }

    public void setLastInterview(String lastInterview) {
        this.lastInterview = lastInterview;
    }

    @Override
    public String toString() {
        return "UserProfile{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", candidateId='" + candidateId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", primarySkillSet='" + primarySkillSet + '\'' +
                ", secondarySkillSet='" + secondarySkillSet + '\'' +
                ", experience='" + experience + '\'' +
                ", designation='" + designation + '\'' +
                ", joiningPeriod='" + joiningPeriod + '\'' +
                ", location='" + location + '\'' +
                ", interviewStatus=" + interviewStatus +
                ", lastInterview=" + lastInterview +
                '}';
    }
}
