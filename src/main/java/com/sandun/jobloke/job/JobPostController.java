package com.sandun.jobloke.job;

import com.sandun.jobloke.service.AllServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/jobpost")
public class JobPostController {

    private final AllServices jobService;

    @Autowired
    public JobPostController(AllServices jobService) {
        this.jobService = jobService;
    }

    @GetMapping
    public List<JobPost> getPosts(){
        System.out.println("Called");
        return jobService.getAllJobs();
    }

    @GetMapping("/{username}")
    public List<JobPost> getJobsByUser(@PathVariable(name = "username") String username){
        return jobService.getAllJobs(username);
    }

    @PostMapping(consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public void addJobPost(@ModelAttribute JobPostRequest request){
        System.out.println(request.getImage().getOriginalFilename());
    }

    @DeleteMapping(path = "/{id}")
    public void deletePost(@PathVariable Integer id){
        jobService.deletePost(id);
    }

    @PutMapping(path = "/{id}")
    public void updatePost(@RequestBody JobPost post, @PathVariable(name = "id") Integer id){
        jobService.updatePost(post,id);
    }
    

}
