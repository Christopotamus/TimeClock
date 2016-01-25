package com.mynameischrisbudd.timeclock;

import java.util.Date;

/**
 * Created by christopher on 1/24/16.
 */
public class Task {

    private String TaskName;
    private Date LogTime;

    public Task(String taskName, Date logTime) {

        TaskName = taskName;
        LogTime = logTime;
    }
    public String getTaskName() {
        return TaskName;
    }

    public void setTaskName(String taskName) {
        TaskName = taskName;
    }

    public Date getLogTime() {
        return LogTime;
    }

    public void setLogTime(Date logTime) {
        LogTime = logTime;
    }



}
