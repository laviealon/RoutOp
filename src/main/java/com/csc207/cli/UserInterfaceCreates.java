package com.csc207.cli;

import com.csc207.domain.FixedTask;
import com.csc207.domain.NonFixedTask;
import com.csc207.domain.Project;
import com.csc207.domain.Week;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Scanner;

public class UserInterfaceCreates {
    /**
     * Gives a signed-up user the option to create a new week. Input mist be 1.
     *
     * @param reader: The scanner in the Main module reading user input.
     * @return user's selection option as an integer.
     */
    public static int createWeek(Scanner reader){
        // Give instructions to the user
        UserInterfacePrints.createWeekMessage();
        String selectedOption = reader.nextLine();
        return Integer.parseInt(selectedOption);
    }

    /**
     * Get info from user about the FixedTask that they want to schedule and return the Task.
     * Collect information from user about task they want to schedule and return a task according to their
     * specifications
     *
     * @param reader: The scanner in Main module reading user input
     * @return the FixedTask that is to be put in the schedule.
     */
    public static FixedTask createFixedTask(Scanner reader, Long userId){
        try{
            System.out.println("What is the name of your task or event?");

        String name = reader.nextLine(); // Get user input

        System.out.println("On what date does your task or event to take place?");
        LocalDate date = UserInterfaceExceptions.getDate(reader);

        System.out.println("At what time does your task or event begin?");
        LocalTime time = UserInterfaceExceptions.getTime(reader);
        LocalDateTime startDateTime = LocalDateTime.of(date, time);

        System.out.println("What is the duration of this task or event?");
        LocalTime duration = UserInterfaceExceptions.getTime(reader);

        return new FixedTask(name, startDateTime, duration, userId);  // Create a FixedTask from this information
        }
        catch(Exception exception) {
            System.out.println(exception.getMessage());
            return createFixedTask(reader, userId);
        }
    }

    /**
     * Get info from user about the NonFixedTask that they want to schedule and return the Task.
     *
     * @param reader: The scanner in Main module reading user input
     * @return the NonFixedTask that is to be put in the schedule.
     */
    public static NonFixedTask createNonFixedTask(Scanner reader, Long userId){
        try {
            System.out.println("What is the name of your task or event?");
            String name = reader.nextLine(); // Get user input

            System.out.println("What is the duration of your task or event?");
            LocalTime duration = UserInterfaceExceptions.getTime(reader); // get task duration

            System.out.println("Please enter the date that this task or event is due before.\n");
            LocalDate dueDate = UserInterfaceExceptions.getDate(reader);

            System.out.println("At what time on that day is your task or event due before?\n");
            LocalTime dueTime = UserInterfaceExceptions.getTime(reader);

            LocalDateTime dueDateTime = LocalDateTime.of(dueDate, dueTime);
            return new NonFixedTask(name, dueDateTime, duration, userId);
        }
        catch(Exception exception){
            System.out.println(exception.getMessage());
            return createNonFixedTask(reader, userId);
        }
    }

    /**
     * Get info from user about the project they want to schedule, and return an array of NonFixedTasks
     * corresponding to the project.
     *
     * @param week: The week in which the nonFixedTask is being scheduled
     * @param reader: The scanner in Main module reading user input
     * @return an array of unscheduled NonFixedTasks corresponding to this project.
     */
    public static NonFixedTask[] createProject(Week week, Scanner reader){
        try {
            System.out.println("What is the name of your project or goal?");

            String name = reader.nextLine(); // Get user input

            System.out.println("What date do you want to start working on this project or goal?\n");
            LocalDate startDate = UserInterfaceExceptions.getDate(reader);

            LocalDateTime dueDateTime = getDueDateTime(reader);

            LocalTime maxHoursPerTask = getMaxHoursPerTask(week, reader, startDate, dueDateTime);

            NonFixedTask[] projectTasks = new NonFixedTask[7];
            for (int i = 0; i < 7; i++) {
                projectTasks[i] = new NonFixedTask(name, dueDateTime, maxHoursPerTask, week.getUserId());
            }
            return projectTasks;
        }
        catch(Exception exception){
            System.out.println(exception.getMessage());
            return createProject(week, reader);
        }
    }

    /** Helper method for createProject which gathers information about the project due date and time
     *
     * @param reader: The scanner in Main module reading user input
     * @return the LocalDateTime object representing the due date for the project
     */
    private static LocalDateTime getDueDateTime(Scanner reader) {
        try {
            System.out.println("What date is this project or goal due by?");

            LocalDate dueDate = UserInterfaceExceptions.getDate(reader);

            System.out.println("At what time on that day is your project or goal due before? \n");
            LocalTime dueTime = UserInterfaceExceptions.getTime(reader);

            return LocalDateTime.of(dueDate, dueTime);
        }
        catch(Exception exception){
            System.out.println(exception.getMessage());
            return getDueDateTime(reader);
        }
    }

    /** Helper method for createProject which gathers information about the maximum amount of time the user can spend
     * on the project at a time
     *
     * @param reader: The scanner in Main module reading user input
     * @param week: the week into which the project is being scheduled
     * @return the LocalTime object representing the maximum amount of hours the user is willing to spend on the project
     * at a time
     */
    private static LocalTime getMaxHoursPerTask(Week week, Scanner reader, LocalDate startDate, LocalDateTime dueDateTime)
    {
        try {
            System.out.println("What is the total number of hours you would like to work on this project? (round to the" +
                    " nearest 0.5)");

            double totalHours = Double.parseDouble(reader.nextLine());
            double minHours = Project.calculateMinHours(week, startDate, dueDateTime, totalHours, 7);
            // Create case to handle when minHours is 0.0
            double maxHours = Project.calculateMaxHoursWeek(week);
            System.out.println("You must work on this project at least " + minHours + " per day and at most " + maxHours +
                    " per day.");
            System.out.println("Please enter the maximum amount of time you would like to work on this project in a given" +
                    "day.");
            return UserInterfaceExceptions.getTime(reader);
        }
        catch(Exception exception){
            System.out.println(exception.getMessage());
            return getMaxHoursPerTask(week, reader, startDate, dueDateTime);
        }
    }

    public static createWeek(Long userId, Scanner reader) {
        LocalDate startDate = getStartDate(reader);
        Day[] days = DaysInjector.constructDayList(startDate);
        week = new Week(userId, days);
    }

    /**
     * helper method for activateCreateOrImport
     * Assuming user starts a new week, ask the user to choose on which date
     * they want their week to start.
     *
     * @param reader: The scanner in Main module reading user input
     * @return the date they input as a LocalDate object.
     */
    private static LocalDate getStartDate(Scanner reader){
        // Give user instructions
        try {
            System.out.println("On which day do you want your week to start?\n");
            return UserInterfaceExceptions.getDate(reader);
        }
        catch(Exception exception){
            System.out.println(exception.getMessage());
            return getStartDate(reader);
        }
    }
}
