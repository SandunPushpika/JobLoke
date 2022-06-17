package com.sandun.jobloke.user;

import com.sandun.jobloke.service.AllServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping( path = "api/v1/users")
public class UserController {

    private AllServices service;

    @Autowired
    public UserController(AllServices service) {
        this.service = service;
    }

    @GetMapping
    public List<ApplicationUser> getUsers(){
        return service.getAllUsers();
    }

    @PostMapping
    public String createUser(@RequestBody ApplicationUser user){
        return service.registerUser(user);
    }

    @GetMapping("/getuser")
    public ApplicationUser getUser(@RequestParam(name = "usernameOremail") String usernameOrEmail){
        System.out.println("Revoke");
        return service.getUser(usernameOrEmail);
    }

    @DeleteMapping("/{username}")
    public void deleteUser(@PathVariable(name = "username") String username ){
        service.deleteUser(username);
    }

    @PutMapping("/{username}")
    public void updateUser(@PathVariable(name = "username") String username,@RequestBody ApplicationUser user){
        service.updateUserDetails(username, user);
    }

}
