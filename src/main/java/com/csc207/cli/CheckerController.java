package com.csc207.cli;

import com.csc207.domain.Checker;
import com.csc207.domain.FixedTask;
import com.csc207.domain.NonFixedTask;
import com.csc207.domain.Week;

public class CheckerController {
    /**
     * Check if a non-fixed task can be scheduled
     * @param week: the week to schedule into
     * @param taskToSchedule: the task to check
     */
    public static boolean checkNonFixedTaskScheduling(Week week, NonFixedTask taskToSchedule){
        return Checker.CheckScheduleNonFixedTask(week, taskToSchedule);
    }

    /**
     * Check if a project task can be scheduled
     * @param week: the week to schedule into
     * @param projectToSchedule: the project check
     */
    public static boolean checkProjectScheduling(Week week,NonFixedTask[] projectToSchedule){
        return Checker.CheckScheduleProject(week, projectToSchedule);
    }

    /**
     * Check if a fixed task can be scheduled
     * @param week: the week to schedule into
     * @param taskToSchedule: the task to check
     */
    public static boolean checkFixedTaskScheduling(Week week, FixedTask taskToSchedule){
        return Checker.CheckScheduleFixedTask(week, taskToSchedule);
    }
}
