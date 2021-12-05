package com.csc207.api;

import com.csc207.domain.Day;
import com.csc207.domain.DaysInjector;
import com.csc207.domain.NonFixedTask;
import com.csc207.domain.Week;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class UserSchedulingControllerTest {
    Week week;
    NonFixedTask t1;
    NonFixedTask t2;
    NonFixedTask[] tasks;
    Day day;

    @Before
    public void setUp(){
        LocalDate startDate = LocalDate.of(2021, 4, 3);
        week = new Week(1L, DaysInjector.constructDayList(startDate));
        day = new Day(startDate);
        t1 = new NonFixedTask("Piano Practice", LocalDateTime.of(2021, 4, 5, 12, 0), LocalTime.of(2, 30), 1L);
        t2 = new NonFixedTask("Piano Practice", LocalDateTime.of(2021, 4, 6, 12, 0), LocalTime.of(2, 30), 1L);
        tasks = new NonFixedTask[]{t2};
    }

    @After
    public void tearDown(){
    }

    @Test
    public void isUsernameAndPasswordInDb() {
    }

    @Test
    public void getUser() {
    }

    @Test
    public void saveUser() {
    }
}
