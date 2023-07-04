package com.csc207.api;

import com.csc207.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

/**
 * This class is responsible for accessing data about the user in the user database.
 */

@RestController
public class UserController {
    @Autowired
    private final UserInteractorDataOut userInteractorDataOut;
    @Autowired
    private final UserInteractorDataIn userInteractorDataIn;

    /**
     * The constructor for the UserController class.
     * @param userInteractorDataOut: The interactor to access the information in the user database.
     * @param userInteractorDataIn: The interactor to save data to the user database.
     */
    public UserController(UserInteractorDataOut userInteractorDataOut, UserInteractorDataIn userInteractorDataIn){
        this.userInteractorDataOut = userInteractorDataOut;
        this.userInteractorDataIn = userInteractorDataIn;
    }


    @GetMapping("/users")
    List<User> all() {
        return userInteractorDataOut.getAllUsers();
    }

    /**
     * Check if the username of this user are in the database.
     *
     * @param username: the username.
     * @return the user associated with the username.
     */
    @GetMapping("/users/{username}")
    @CrossOrigin
    public User getUserFromUsername(@PathVariable String username){
        return this.userInteractorDataOut.getUserFromUsername(username);
    }


    /**
     * Checks if the username is in the database.
     * @param username: The username.
     * @return whether the username is in the database
     */
    @GetMapping("/users/check/{username}")
    public boolean isUsernameInDb(@PathVariable String username){
        return this.userInteractorDataOut.isUsernameInDb(username);
    }

    /**
     * Save this User entity to the database.
     *
     * @param user: the User.
     */
    @Transactional
    @PostMapping("/users")
    @CrossOrigin
    public void saveUser(@RequestBody User user){
        this.userInteractorDataIn.saveUser(user);
    }
}