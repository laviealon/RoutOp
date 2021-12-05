package com.csc207.cli;
import com.csc207.api.*;
import com.csc207.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class is responsible for executing all the user interactions.
 */

@Service
public class UserInterface {
    @Autowired
    private UserController userController;
    @Autowired
    private WeekSerializableInteractorDataOut weekSerializableInteractor;
    @Autowired
    private UserInteractorDataOut userInteractor;
    @Autowired
    private WeekController weekController;
    @Autowired
    private final TaskSerializableInteractorDataOut taskSerializableInteractorDataOut;

    /**
     * The constructor for the UserInterface class.
     * @param uc: The user controller object to obtain the user from the database.
     * @param wsi: The week serializable interactor to obtain the week from the database.
     * @param ui: The user interactor to check the database.
     * @param tsido: The task serializable interactor to get data from the database.
     * @param wc: The week controller object to save week to a database.
     */
    public UserInterface(UserController uc, WeekSerializableInteractorDataOut wsi, UserInteractorDataOut ui,
                         TaskSerializableInteractorDataOut tsido, WeekController wc){
        this.userController = uc;
        this.weekSerializableInteractor = wsi;
        this.userInteractor = ui;
        this.taskSerializableInteractorDataOut = tsido;
        this.weekController = wc;
    }



    /**
     * Sign user up or in based on whether they have an account from previous input prompt.
     *
     * @param haveAccount: whether they have an account.
     * @param reader: the Scanner to get input.
     * @return the next method, which signs them in or up depending on their input.
     */
    public Long activateSignInOrSignUp(boolean haveAccount, Scanner reader){
        if (haveAccount){
            return signIn(reader);
        }
        else {
            return signUp(reader);
        }
    }

    /**
     * Signs a user in.
     *
     * @param reader: the scanner for user input.
     * @return the id for the user.
     */
    public long signIn(Scanner reader){
        System.out.println("Please enter your username.");
        String username = reader.nextLine();
        System.out.println("Please enter your password.");
        String password = reader.nextLine();
        if (this.userInteractor.checkSignIn(username, password)){ // checks whether the username and password exist and match up
            long userId = this.userInteractor.getUserIdByUsername(username);
            System.out.println(this.weekSerializableInteractor.getWeekSerializableByUserId(userId)); // show the user their week
            return userId;
        }
        else{
            System.out.println("Incorrect username or password. \n ");
            return signIn(reader);
        }
    }

    /**
     * signs up a user
     *
     * @param reader: the scanner for user input
     * @return the id for the user
     */
    private long signUp(Scanner reader) {
        System.out.println("Please enter a username.");
        String username = reader.nextLine();
        System.out.println("Please enter a password.");
        printPasswordRequirements();
        String password = reader.nextLine();
        User newUser = new User(username, password);
        this.userController.saveUser(newUser);
        return newUser.getId();
    }

    /**
     * Prints password creation requirements.
     */
    private static void printPasswordRequirements(){
        System.out.println("MUST contain at least 12 characters");
        System.out.println("MUST contain at least one uppercase letter");
        System.out.println("MUST contain at least one lowercase letter");
        System.out.println("MUST contain at least one number");
        System.out.println("MUST contain at least one special character");
        System.out.println("MUST not contain two or more identical characters in a row");
        System.out.println("MUST not match commonly used password patterns");
    }
    }







