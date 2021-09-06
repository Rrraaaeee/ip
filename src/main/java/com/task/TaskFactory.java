package com.task;

import com.command.Command;
import com.time.Time;

public class TaskFactory {

    public TaskFactory() {}

    /**
     * Function to create a task of type todo, deadline, or event
     **/
    public TaskBase makeTask(Command cmd) {
        Time timeInfo = cmd.getTimeInfo();
        String taskDescription = cmd.getTaskDescription();
        if (cmd.getTaskType() == TaskType.TODO) {
            return new TaskTodo(TaskType.TODO, timeInfo, taskDescription);
        } else if (cmd.getTaskType() == TaskType.DEADLINE) {
            return new TaskDeadline(TaskType.DEADLINE, timeInfo, taskDescription);
        } else if (cmd.getTaskType() == TaskType.EVENT) {
            return new TaskEvent(TaskType.EVENT, timeInfo, taskDescription);
        } else {
            System.out.println("[TASK FACTORY] Default task is todo type");
            return new TaskTodo(TaskType.TODO, timeInfo, taskDescription);
        }
    }
}
