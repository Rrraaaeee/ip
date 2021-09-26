package com.task;

public abstract class TaskBase {
    private TaskType taskType;
    private String taskTime;
    private String taskDescription;
    private boolean isDone;

    /**
     * Constructor of task that accepts tasktype, time and taskcontent
     **/
    public TaskBase(TaskType taskType, String taskTime, String taskDescription) {
        this.taskType = taskType;
        this.taskTime = taskTime;
        this.taskDescription = taskDescription;
        this.isDone = false;
    }

    public void markDone() {
        this.isDone = true;
    }

    /**
     * Utility to print all info related to task for list command
     **/
    public String toString() {
        String s = new String("");
        s += String.format("[%s] [%c] %s | ",
                    getTaskSymbol(),
                    isDone ? 'X' : '_',
                    taskDescription);
        s += taskTime.toString();
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
