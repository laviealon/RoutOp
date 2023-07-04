package com.csc207.api;

import com.csc207.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import java.util.List;

/**
 * This class is responsible for accessing user information in the database.
 */

@Configuration
public class UserInteractorDataOut {
    @Autowired
    private final UserRepository userRepo;

    /**
     * The constructor for the UserInteractorDataOut.
     * @param userRepo: The database that the class accesses.
     */
    public UserInteractorDataOut(UserRepository userRepo){
        this.userRepo = userRepo;
    }


    /**
     * Check if the username and password match up in the database.
     *
     * @param username: the username input by the user
     * @param password: the password input by the user
     * @return whether they match up
     */
    public boolean checkSignIn(String username, String password){
        boolean existsUser = this.userRepo.existsUserByUsername(username);
        if (existsUser) {
            String expectedPassword = this.userRepo.getUserByUsername(username).get(0).getPassword();
            return password.equals(expectedPassword);
        }
        return false;
    }

    /**
     * Get the id of a user.
     *
     * @param username: the Username of the user.
     */
    public long getUserIdByUsername(String username){
        return this.userRepo.getUserByUsername(username).get(0).getId();
    }

    /**
     * Checks if the username is in the database.
     *
     * @param username: The username that is checked if it is in the database.
     * @return If the username is in the database or not.
     */
    public boolean isUsernameInDb(String username) {
        return this.userRepo.existsUserByUsername(username);
    }

    /**
     * Gets the user of the given username.
     * @param username: The username of the user that will be found in the database.
     * @return The User object of the username
     */
    public User getUserFromUsername(String username) throws IndexOutOfBoundsException {
        return this.userRepo.getUserByUsername(username).get(0);
    }

    /**
     * Gets all users from the database.
     * @return a list of all the users.
     */
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }
}
