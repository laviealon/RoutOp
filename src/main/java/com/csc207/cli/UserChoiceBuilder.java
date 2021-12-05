package com.csc207.cli;

import com.csc207.api.*;
import com.csc207.domain.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class UserChoiceBuilder {

    @Autowired
    private final WeekController weekController;

    /**
     * The constructor for the UserChoiceBuilder class.
     * @param wc: The week controller object to save week to a database.
     */
    public UserChoiceBuilder(WeekController wc){
        this.weekController = wc;
    }

    /** Takes the selection of the user and allows the user to create their schedule with the given week, start time,
     * or to import an existing schedule
     *
     * @param userId: the id of the user
     * @param selection: The selection of the user, whether they want to import or create their schedule
     * @param reader: The scanner in Main module reading user input
     */
    public Week implementCreateOrImport(long userId, int selection, Scanner reader){
        Week week;
        if (selection == 1) {
            UserInterfaceCreates.createWeek(userId, reader);
        } else if (selection == 2) { // use user id to retrieve the user's week serializable, convert it to week
            this.weekController.importWeek();
        }
        return week;
    }

    /** Takes the selection and week that is given and finds whether what type of task is needed to be scheduled and
     * schedules the task
     *
     * @param week: the week that the task will be scheduled in
     * @param selection: the selection from the user about which type of task they would like to put
     * @param reader: The scanner in Main module reading user input
     */
    public static void schedulingDecision(Week week, int selection, Scanner reader){
        if (selection == 1) {
            selectsOne(week, reader);
        } else if (selection == 2) {
            selectsTwo(week, reader);
        } else if (selection == 3) {
            selectsThree(week, reader);
        } else if (selection == 4){
            // convert the week into WeekSerializable and TaskSerializable, and save to database
            wc.saveWeek(week);
        } else {
            System.out.println("Please enter a valid option (1, 2, 3, or 4).");
        }
    }


    /**
     * Helper method for schedulingDecision. Executes code for when the user selects selection 3.
     * @param week: The week that the user wants to schedule their project.
     * @param reader: The Scanner object to take in inputs.
     */
    private static void selectsThree(Week week, Scanner reader) {
        NonFixedTask[] projectTasksToSchedule = UserInterfaceCreates.createProject(week, reader);
        if(!CheckerController.checkProjectScheduling(week, projectTasksToSchedule)){
            System.out.println("This project can't be scheduled");}
        else{
            SchedulingController.activateProjectScheduling(week, projectTasksToSchedule);}
    }

    /**
     * Helper method for schedulingDecision. Executes code for when the user selects selection 2.
     * @param week: The week that the user wants to schedule their NonFixedTask.
     * @param reader: The Scanner object to take in inputs.
     */
    private static void selectsTwo(Week week, Scanner reader) {
        NonFixedTask taskToSchedule = UserInterfaceCreates.createNonFixedTask(reader, week.getUserId());
        if(!CheckerController.checkNonFixedTaskScheduling(week, taskToSchedule)){
            System.out.println("This task can't be scheduled");}
        else{
            SchedulingController.activateNonFixedTaskScheduling(week, taskToSchedule);}
    }

    /**
     * Helper method for schedulingDecision. Executes code for when the user selects selection 1.
     * @param week: The week that the user wants to schedule their FixedTask.
     * @param reader: The Scanner object to take in inputs.
     */
    private static void selectsOne(Week week, Scanner reader) {
        FixedTask taskToPut = UserInterfaceCreates.createFixedTask(reader, week.getUserId());
        if(!CheckerController.checkFixedTaskScheduling(week, taskToPut)){
            System.out.println("This task can't be scheduled");}
        else{
            SchedulingController.activateFixedTaskScheduling(week, taskToPut);}
    }

}
