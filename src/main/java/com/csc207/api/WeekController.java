package com.csc207.api;

import com.csc207.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.ArrayList;

/**
 * This class is responsible for saving a week and its tasks into the database using weekSerializableInteractorDataIn
 * and taskSerializableInteractorDataIn.
 */

@RestController
public class WeekController {
    @Autowired
    private final WeekSerializableInteractorDataIn weekSerializableInteractorDataIn;
    @Autowired
    private final TaskSerializableInteractorDataIn taskSerializableInteractorDataIn;
    @Autowired
    private WeekSerializableInteractorDataOut weekSerializableInteractorDataOut;
    @Autowired
    private TaskSerializableInteractorDataOut taskSerializableInteractorDataOut;

    /**
     * The constructor for the WeekController class.
     * @param weekSerializableInteractorOut: The interactor used to access data from the week database.
     * @param weekSerializableInteractorDataIn: The interactor used to save data to the week database.
     * @param taskSerializableInteractorDataIn: The interactor used to save data to the task database.
     * @param taskSerializableInteractorDataOut: The interactor used to access data from the task database.
     */
    public WeekController(WeekSerializableInteractorDataOut weekSerializableInteractorOut,
                          WeekSerializableInteractorDataIn weekSerializableInteractorDataIn,
                          TaskSerializableInteractorDataIn taskSerializableInteractorDataIn,
                          TaskSerializableInteractorDataOut taskSerializableInteractorDataOut) {
        this.weekSerializableInteractorDataIn = weekSerializableInteractorDataIn;
        this.taskSerializableInteractorDataIn = taskSerializableInteractorDataIn;
    }

    /**
     * Saves the week and its tasks to the week database and the task database, respectively.
     * @param week: The week that will be saved to the database.
     */
    @PostMapping("/weeks")
    public void saveWeek(@RequestBody Week week) {
        // convert to week serializable
        WeekSerializable convertedWeek = WeekToSerializableAdapter.WeekToWeekSerializable(week);
        // convert to task serializable
        ArrayList<TaskSerializable> convertedTasks = WeekToSerializableAdapter.WeekToTaskSerializable(week);
        // save weekSerializable
        this.weekSerializableInteractorDataIn.saveWeekSerializable(convertedWeek);
        // save taskSerializable
        for(TaskSerializable task: convertedTasks) {
            this.taskSerializableInteractorDataIn.saveTaskSerializable(task);
        }
    }

    /**
     * Retrieve the week and its tasks from the week database and the task database, respectively.
     * @param userId: The userId of the user who has a stored week
     */
    @GetMapping("/weeks/{userId}")
    public Week importWeek(@PathVariable long userId) {
        Week week;
        WeekSerializable weekSers = this.weekSerializableInteractorDataOut.getWeekSerializableByUserId(userId);
        this.weekSerializableInteractorDataOut.removeWeekSerializableByUserId(userId);
        ArrayList<TaskSerializable> tasksSers = this.taskSerializableInteractorDataOut.getTasksByUserId(userId);
        this.taskSerializableInteractorDataOut.removeTaskSerializablesByUserId(userId);
        week = SerializableToWeekAdapter.SerializableToWeek(weekSers, tasksSers);
        return week;
    }
}
