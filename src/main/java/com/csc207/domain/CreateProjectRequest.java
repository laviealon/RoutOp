package com.csc207.domain;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class CreateProjectRequest {
    private final long userid;
    private final String name;
    private final LocalTime maxHoursPerTask;
    private final LocalDateTime dueDateTime;
    private final LocalDateTime startDateTime;

    public CreateProjectRequest(long userid, String name, LocalTime maxHoursPerTask, LocalDateTime dueDateTime, LocalDateTime startDateTime){
        this.userid = userid;
        this.name = name;
        this.maxHoursPerTask = maxHoursPerTask;
        this.dueDateTime = dueDateTime;
        this.startDateTime = startDateTime;
    }

    public long getUserid() {
        return userid;
    }

    public LocalTime getMaxHoursPerTask() {
        return maxHoursPerTask;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getDueDateTime() {
        return dueDateTime;
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }
}
