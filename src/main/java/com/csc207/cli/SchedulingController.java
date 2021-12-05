package com.csc207.cli;

import com.csc207.domain.*;

import java.util.Scanner;

/**
 * This class is responsible for initiating scheduling and putting of tasks and projects into weeks and checking if a
 * task or project can be scheduled in a week.
 */

public class SchedulingController {

    /**
     * Initiates scheduling and putting of a fixed task if user chooses
     * to add one to their schedule in UI.
     * @param week: the week to schedule into
     * @param taskToPut: the task to schedule
     */
    public static void activateFixedTaskScheduling(Week week, FixedTask taskToPut){
        Putter.putTask(week, taskToPut);
    }

    /**
     * Initiates scheduling and putting of a non-fixed task if user chooses
     * to add one to their schedule in UI.
     * @param week: the week to schedule into
     * @param taskToSchedule: the task to schedule
     */
    public static void activateNonFixedTaskScheduling(Week week, NonFixedTask taskToSchedule){
        NonFixedTask NonFixedTaskToPut = Scheduler.ScheduleTaskInWeek(week, taskToSchedule);
        Putter.putTask(week, NonFixedTaskToPut);
    }

    /**
     * Initiate scheduling and putting of project based on user preferences
     * @param week: the week to schedule into
     * @param projectTasksToSchedule: the list of tasks to schedule
     */
    public static void activateProjectScheduling(Week week, NonFixedTask[] projectTasksToSchedule){
        NonFixedTask[] projectTasksToPut = Scheduler.ScheduleProject(week, projectTasksToSchedule);
        Putter.putProject(projectTasksToPut[0].getName(), week, projectTasksToPut);
    }

