package com.sandun.jobloke.job;

import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

public class JobPostRequest {
    private String JobTitle;
    private String JobDes;
    private String JobExpireDate;
    private String JobUrl;
    private String ApplicableWays;
    private MultipartFile image;

    public JobPostRequest() {
    }

    public JobPostRequest(String jobTitle, String jobDes, LocalDate jobPostDate, String jobExpireDate, String jobUrl, String applicableWays, MultipartFile image) {
        JobTitle = jobTitle;
        JobDes = jobDes;
        JobExpireDate = jobExpireDate;
        JobUrl = jobUrl;
        ApplicableWays = applicableWays;
        this.image = image;
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

    public String getJobExpireDate() {
        return JobExpireDate;
    }

    public void setJobExpireDate(String jobExpireDate) {
        JobExpireDate = jobExpireDate;
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

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }
}
