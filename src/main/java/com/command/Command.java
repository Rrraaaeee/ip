package com.command;

import com.task.TaskType;

public class Command {

    private CommandType commandType;
    private TaskType taskType;
    private String taskDescription;
    private String taskTimeInfo;

    /**
     * Constructor
     */
    public Command() {
        this.commandType = CommandType.INVALID;
        this.taskType = TaskType.INVALID;
        this.taskTimeInfo = "";
        this.taskDescription = "";
    }

    /**
     * Constructor
     */
    public Command(CommandType commandType) {
        this.commandType = commandType;
        this.taskType = TaskType.INVALID;
        this.taskTimeInfo = "";
        this.taskDescription = "";
    }

    /**
     * get command type
     */
    public CommandType getCommandType() {
        return commandType;
    }

    /**
     * get task description
     */
    public String getTaskDescription() {
        return taskDescription;
    }

    /**
     * get time info
     */
    public String getTimeInfo() {
        return taskTimeInfo;
    }

    /**
     * get task type
     */
    public TaskType getTaskType() {
        return taskType;
    }

    /**
     * set command type, used by parser
     */
    public void setCommandType(CommandType commandType) {
        this.commandType = commandType;
        return;
    }

    /**
     * set task description, used by parser
     */
    public void setTaskDescription(String description) {
        this.taskDescription = description;
        return;
    }

    /**
     * append to task description, used by parser
     */
    public void appendTaskDescription(String description) {
        this.taskDescription += description;
        return;
    }

    /**
     * set time info, used by parser
     */
    public void setTimeInfo(String time) {
        this.taskTimeInfo = time;
        return;
    }

    /**
     * set task type. Used by parser
     */
    public void setTaskType(TaskType taskType) {
        this.taskType = taskType;
        return;
    }

    /**
     * Check if command is the expected type
     */
    public Boolean isType(CommandType commandType) {
        return (this.commandType == commandType);
    }



}
