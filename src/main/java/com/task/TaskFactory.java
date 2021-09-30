package com.task;

import com.command.Command;
import com.time.Time;

public class TaskFactory {

    public TaskFactory() {}

    /**
     * Function to create a task of type todo, deadline, or event
     **/
    public TaskBase makeTask(Command command) {
        Time timeInfo = command.getTimeInfo();
        String taskDescription = command.getTaskDescription();
        if (command.getTaskType() == TaskType.TODO) {
            return new TaskTodo(TaskType.TODO, null, taskDescription);
        } else if (command.getTaskType() == TaskType.DEADLINE) {
            return new TaskDeadline(TaskType.DEADLINE, timeInfo, taskDescription);
        } else if (command.getTaskType() == TaskType.EVENT) {
            return new TaskEvent(TaskType.EVENT, timeInfo, taskDescription);
        } else {
            return new TaskTodo(TaskType.TODO, timeInfo, taskDescription);
        }
    }

    /**
     * Function to create a task of type todo, deadline, or event
     **/
    public TaskBase makeTask(TaskType taskType, String taskDescription, Time timeInfo) {
        if (taskType == TaskType.TODO) {
            return new TaskTodo(TaskType.TODO, timeInfo, taskDescription);
        } else if (taskType == TaskType.DEADLINE) {
            return new TaskDeadline(TaskType.DEADLINE, timeInfo, taskDescription);
        } else if (taskType == TaskType.EVENT) {
            return new TaskEvent(TaskType.EVENT, timeInfo, taskDescription);
        } else {
            return new TaskTodo(TaskType.TODO, timeInfo, taskDescription);
        }
    }
}
