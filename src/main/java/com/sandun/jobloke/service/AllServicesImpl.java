package com.sandun.jobloke.service;

import com.sandun.jobloke.job.JobPost;
import com.sandun.jobloke.repo.JobRepository;
import com.sandun.jobloke.repo.UserRepository;
import com.sandun.jobloke.user.ApplicationUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class AllServicesImpl implements AllServices, UserDetailsService {
    private final JobRepository jobRepo;
    private final UserRepository userRepo;
    private final PasswordEncoder encoder;

    @Autowired
    public AllServicesImpl(JobRepository jobRepo, UserRepository userRepository, PasswordEncoder encoder) {
        this.jobRepo = jobRepo;
        this.userRepo = userRepository;
        this.encoder = encoder;
    }

    @Override
    public ApplicationUser getUser(String usernamerOrEmail){
        if(usernamerOrEmail.contains("@")){
            return userRepo.findUserByEmail(usernamerOrEmail).orElseThrow(() -> new UsernameNotFoundException("Email Not Found!"));
        }else{
            System.out.println("got it");
            return userRepo.findById(usernamerOrEmail).orElseThrow(() -> new UsernameNotFoundException("Email Not Found!"));
        }
    }

    @Override
    public String registerUser(ApplicationUser user){
        if(userRepo.findUserByEmail(user.getEmail()).isPresent() || userRepo.findById(user.getUsername()).isPresent()){
            return "Username or email is already in use!";
        }else{
            user.setPassword(encoder.encode(user.getPassword()));
            userRepo.save(user);
            return "User Created Successfully";
        }
    }

    @Override
    public void deleteUser(String username){
        userRepo.deleteById(username);
    }

    @Override
    @Transactional
    public void updateUserDetails(String username,ApplicationUser user){
        if(userRepo.findById(username).isPresent()){
            user.setPassword(encoder.encode(user.getPassword()));
            userRepo.save(user);
        }
    }

    @Override
    public List<ApplicationUser> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public List<JobPost> getAllJobs(){
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return jobRepo.findJobsCreatedByUser(username).orElseThrow(() -> new RuntimeException("Error Occured"));
    }

    @Override
    public List<JobPost> getAllJobs(String username) {
        return jobRepo.findJobsCreatedByUser(username).orElseThrow(() -> new RuntimeException("Error Occured"));
    }

    @Override
    public void addJobPost(JobPost jobPost){
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        jobPost.setJobPostDate(LocalDate.now());
        System.out.println(jobPost.getJobPostDate());
        jobRepo.save(jobPost);
        jobRepo.insertToAppliedJob(jobPost.getJobID(),username);
    }

    @Override
    public void deletePost(Integer id){
        jobRepo.deleteById(id);
    }


    @Override
    @Transactional
    public void updatePost(JobPost post,Integer id){
        Optional<JobPost> jpost = jobRepo.findById(id);
        if(jpost.isPresent()){
            JobPost jp = jpost.get();
            jp.setJobID(post.getJobID());
            jp.setJobDes(post.getJobDes());
            jp.setJobExpireDate(post.getJobExpireDate());
            jp.setJobPostDate(post.getJobPostDate());
            jp.setJobTitle(post.getJobTitle());
            jp.setJobUrl(post.getJobUrl());
            jp.setApplicableWays(post.getApplicableWays());

            jobRepo.save(jp);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ApplicationUser user = userRepo.findById(username).orElseThrow(()-> new UsernameNotFoundException("Username doesn't exist!"));

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getAuthority()));

        return new User(user.getUsername(),user.getPassword(),authorities);
    }
}
