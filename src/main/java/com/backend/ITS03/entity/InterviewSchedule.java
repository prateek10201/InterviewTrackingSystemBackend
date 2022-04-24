package com.backend.ITS03.entity;


import javax.persistence.*;
import java.io.Serializable;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

@Entity
public class InterviewSchedule implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private long id;
    private String interviewId;
    private String candidateId;
    private String userId;
    private String interviewType;
    private String interviewDate;
    private String rating;

    public InterviewSchedule() {
    }

    public InterviewSchedule(String interviewId, String candidateId, String userId, String interviewType, String interviewDate, String rating) {

        this.interviewId = interviewId;
        this.candidateId = candidateId;
        this.userId = userId;
        this.interviewType = interviewType;
        this.interviewDate = interviewDate;
        this.rating = rating;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getInterviewId() {
        return interviewId;
    }

    public void setInterviewId(String interviewId) {
        this.interviewId = interviewId;
    }

    public String getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(String candidateId) {
        this.candidateId = candidateId;
    }

    public String getInterviewType() {
        return interviewType;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setInterviewType(String interviewType) {
        this.interviewType = interviewType;
    }

    public String getInterviewDate() {
        return interviewDate;
    }

    public void setInterviewDate(String interviewDate) {
        this.interviewDate = interviewDate;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "InterviewSchedule{" +
                "id=" + id +
                ", interviewId='" + interviewId + '\'' +
                ", candidateId='" + candidateId + '\'' +
                ", userId='" + userId + '\'' +
                ", interviewType='" + interviewType + '\'' +
                ", interviewDate=" + interviewDate +
                ", rating='" + rating + '\'' +
                '}';
    }
}
