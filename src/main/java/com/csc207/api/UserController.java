package com.csc207.api;

import com.csc207.domain.User;
import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private final UserInteractorDataOut userInteractorDataOut;
    @Autowired
    private final UserInteractorDataIn userInteractorDataIn;

    public UserController(UserInteractorDataOut userInteractorDataOut, UserInteractorDataIn userInteractorDataIn){
        this.userInteractorDataOut = userInteractorDataOut;
        this.userInteractorDataIn = userInteractorDataIn;
    }


    @GetMapping("/helloworld")
    @ResponseBody
    String helloWorld() {
        return "Hello world!";
    }

    @GetMapping("/allusers")
    @ResponseBody
    List<User> all() {
        return userInteractorDataOut.getAllUsers();
    }

    /**
     * Check if the username of this user are in the database.
     *
     * @param username: the username.
     * @return whether this username-password pair is in the database.
     */
    @GetMapping("/allusers/{username}")
    @ResponseBody
    public boolean isUsernameInDb(@PathVariable String username){
        return this.userInteractorDataOut.isUsernameInDb(username);
    }

//    /**
//     * Returns a user object from the database based on the user's username.
//     * @param username: the username.
//     * @return the user associated with this username.
//     */
//    public User getUser(String username){
//        return this.userInteractorDataOut.getUserFromUsername(username);
//    }

    /**
     * Save this User entity to the database.
     *
     * @param user: the User.
     */
    @Transactional
    @PostMapping("/allusers")
    public void saveUser(@RequestBody User user){
        this.userInteractorDataIn.saveUser(user);
    }
}