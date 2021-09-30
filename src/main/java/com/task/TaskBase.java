package com.task;

import com.time.Time;

public abstract class TaskBase {
    private TaskType taskType;
    private Time taskTime;
    private String taskDescription;
    private boolean isDone;

    /**
     * Constructor of task that accepts tasktype, time and taskcontent
     **/
    public TaskBase(TaskType taskType, Time taskTime, String taskDescription) {
        this.taskType = taskType;
        this.taskTime = taskTime;
        this.taskDescription = taskDescription;
        this.isDone = false;
    }

    public void markDone() {
        this.isDone = true;
    }

    public boolean nameContains(String taskName) {
        return taskDescription.contains(taskName);
    }

    /**
     * Utility to print all info related to task for list command
     **/
    public String toString() {
        String s = new String("");
        s += String.format("[%s] [%c] %s ",
                    getTaskSymbol(),
                    isDone ? 'X' : '_',
                    taskDescription);
        if (taskTime != null) {
            s += taskTime.toString();
        }
        return s;
    }

    private String getTaskSymbol() {
        if (taskType == TaskType.TODO) {
            return "T";
        } else if (taskType == TaskType.DEADLINE) {
            return "D";
        } else if (taskType == TaskType.EVENT) {
            return "E";
        } else {
            return "?";
        }

    }
}
