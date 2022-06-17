package com.sandun.jobloke.service;

import com.sandun.jobloke.job.JobPost;
import com.sandun.jobloke.user.ApplicationUser;

import java.util.List;

public interface AllServices {
    //For Users
    ApplicationUser getUser(String usernamerOrEmail);
    String registerUser(ApplicationUser user);
    void deleteUser(String username);
    void updateUserDetails(String username,ApplicationUser user);
    List<ApplicationUser> getAllUsers();

    //For Job posts
    List<JobPost> getAllJobs();
    List<JobPost> getAllJobs(String username);
    void addJobPost(JobPost jobPost);
    void deletePost(Integer id);
    void updatePost(JobPost post,Integer id);
}
