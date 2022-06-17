package com.sandun.jobloke.job;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class JobPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int JobID;

    private String JobTitle;
    private String JobDes;
    private LocalDate JobPostDate;
    private LocalDate JobExpireDate;
    private String JobUrl;
    private String ApplicableWays;
    @Lob
    private byte[] image;

    public JobPost() {
    }

    public JobPost(String jobTitle, String jobDes, LocalDate jobPostDate, LocalDate jobExpireDate, String jobUrl, String applicableWays, byte[] image) {
        JobTitle = jobTitle;
        JobDes = jobDes;
        JobPostDate = jobPostDate;
        JobExpireDate = jobExpireDate;
        JobUrl = jobUrl;
        ApplicableWays = applicableWays;
        this.image = image;
    }

    public JobPost(int jobID, String jobTitle, String jobDes, LocalDate jobPostDate, LocalDate jobExpireDate, String jobUrl, String applicableWays, byte[] image) {
        JobID = jobID;
        JobTitle = jobTitle;
        JobDes = jobDes;
        JobPostDate = jobPostDate;
        JobExpireDate = jobExpireDate;
        JobUrl = jobUrl;
        ApplicableWays = applicableWays;
        this.image = image;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getJobUrl() {
        return JobUrl;
    }

    public void setJobUrl(String jobUrl) {
        JobUrl = jobUrl;
    }

    public String getApplicableWays() {
        return ApplicableWays;
    }

    public void setApplicableWays(String applicableWays) {
        ApplicableWays = applicableWays;
    }
    public int getJobID() {
        return JobID;
    }

    public void setJobID(int jobID) {
        JobID = jobID;
    }

    public String getJobTitle() {
        return JobTitle;
    }

    public void setJobTitle(String jobTitle) {
        JobTitle = jobTitle;
    }

    public String getJobDes() {
        return JobDes;
    }

    public void setJobDes(String jobDes) {
        JobDes = jobDes;
    }

    public LocalDate getJobPostDate() {
        return JobPostDate;
    }

    public void setJobPostDate(LocalDate jobPostDate) {
        JobPostDate = jobPostDate;
    }

    public LocalDate getJobExpireDate() {
        return JobExpireDate;
    }

    public void setJobExpireDate(LocalDate jobExpireDate) {
        JobExpireDate = jobExpireDate;
    }

    @Override
    public String toString() {
        return "JobPost{" +
                "JobID=" + JobID +
                ", JobTitle='" + JobTitle + '\'' +
                ", JobDes='" + JobDes + '\'' +
                ", JobPostDate=" + JobPostDate +
                ", JobExpireDate=" + JobExpireDate +
                '}';
    }
}
