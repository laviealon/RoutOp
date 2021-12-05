package com.csc207.cli;

import java.util.Objects;
import java.util.Scanner;

public class UserInterfaceMC {


    /**
     * Starts the program with a welcome message and checks whether the user wants to sign in or sign up.
     *
     * @param reader: the scanner for user input
     * @return the id for the user
     */
    public boolean signInOrSignUp(Scanner reader){
        printWelcomeMessage();
        String response = reader.nextLine(); // y or n
        if (Objects.equals(response, "y")){
            return true;
        }
        else if (Objects.equals(response, "n")){
            return false;
        }
        else{
            System.out.println("Please enter a valid option.");
            return signInOrSignUp(reader); // start again
        }
    }


    /**
     * Give the user the option of scheduling a type of duty.
     *  - If the user inputs 1, program schedules a FixedTask.
     *  - If the user inputs 2, program schedules a NonFixedTask.
     *  - If the user inputs 3, program schedules a project.
     *  - If the user inputs 4, program ends
     *
     * @param reader: The scanner in Main module reading user input
     * @return user's selected option as an integer.
     */
    public static int scheduleDuty(Scanner reader){
        // Give user instructions
        scheduleOptionsMessage();
        String selectedOption = reader.nextLine(); // Get user input
        return Integer.parseInt(selectedOption);
    }

    /**
     * Gives a signed-in user the option to either create or import their week calendar.
     *  - If the user inputs 1, program creates a week calendar.
     *  - If the user inputs 2, program imports a week calendar.
     *
     * @param reader: The scanner in the Main module reading user input.
     * @return user's selected option as an integer.
     */
    public static int createOrImportWeek(Scanner reader){
        // Give instructions to the user
        createOrImportWeekMessage();
        String selectedOption = reader.nextLine();  // Read user input
        return Integer.parseInt(selectedOption);
    }

    /**
     * Options presented to user for whether they want to create a new week or get one.
     */
    private static void createOrImportWeekMessage(){
        System.out.println("Let's begin. Enter");
        System.out.println("    - 1 to start a new week schedule");
        System.out.println("    - 2 use your previously saved week schedule");
    }

    /**
     * Welcome message which greets user when they initiate the program
     */
    private static void printWelcomeMessage(){
        System.out.println("Hi there! Welcome to RoutOp, the app built for optimizing your week.");
        System.out.println("The app looks at your week's fixed schedule (for example: meetings, exercise, or " +
                "classes), and then schedules all your flexible duties in their optimal time slot. This way," +
                "RoutOp helps you maximize your executive output each week! \n");
        System.out.println("Do you have an account with us? (y/n)");
    }


    /**
     * Options presented to user for which kind of scheduling process they would like to initiate.
     * If they choose option 4, they exit the program
     */
    private static void scheduleOptionsMessage(){
        System.out.println("Schedule one or more duties. Enter:");
        System.out.println("    - 1 to schedule a task or event that is part of your week's fixed schedule");
        System.out.println("    - 2 to schedule a task or event that is flexible, which RoutOp will find the best" +
                "time slot for based on your fixed schedule.");
        System.out.println("    - 3 to enter information about a project or goal you are working towards, which " +
                "RoutOp will find time during the week for you to work on based on your fixed schedule as well " +
                "as the information you enter about the project.");
        System.out.println("    - 4 to exit the program");
    }
}
