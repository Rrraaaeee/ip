package com.command;

import com.task.TaskBase;
import com.task.TaskFactory;
import com.task.TaskManager;

public class CommandHandler {

    private TaskManager taskManager;
    private TaskFactory taskFactory;

    /**
     * Constructor to create taskManager and taskFactory
     **/
    public CommandHandler() {
        taskManager = new TaskManager();
        taskFactory = new TaskFactory();
    }

    /**
     * Handle command
     * @param cmd
     */
    public void handlerCommand(Command cmd) {
        CommandType cmdType = cmd.getCommandType();
        System.out.println("____________________________________________________________");
        if (cmdType == CommandType.LIST) {
            handleCommandList();
        } else if (cmdType == CommandType.ADD) {
            handleCommandAdd(cmd);
        } else if (cmdType == CommandType.DONE) {
            handleCommandDone(cmd);
        } else if (cmdType == CommandType.DELETE) {
            handleCommandDelete();
        } else if (cmdType == CommandType.FIND) {
            handleCommandFind();
        } else if (cmdType == CommandType.BYE) {
            handleCommandBye();
        } else if (cmdType == CommandType.INVALID) {
            handleCommandInvalid();
        } else {
            handleCommandInvalid();
        }

        System.out.println("____________________________________________________________");
    }

    private void handleCommandList() {
        System.out.println("list");
        System.out.println("---------------");
        taskManager.listAllTasks();
    }

    private void handleCommandAdd(Command cmd) {
        System.out.println("added: " + cmd.getTaskDescription());
        System.out.println("---------------");
        TaskBase task = taskFactory.makeTask(cmd);
        if (task != null) {
            taskManager.addTask(task);
        }
    }

    protected void handleCommandDone(Command cmd) {
        // TODO: Add try catch for nullpointerexception
        try {
            Integer taskId = Integer.parseInt(cmd.getTaskDescription().strip());
            System.out.println("Marking task " + taskId + " as done");
            System.out.println("---------------");
            taskManager.markTaskDone(taskId);
        } catch (Exception e) {
            System.out.println("Please specify a valid task when marking task as done");
        }
    }


    private void handleCommandDelete() {
        System.out.println("delete");
        System.out.println("---------------");
    }

    private void handleCommandFind() {
        System.out.println("find");
        System.out.println("---------------");
    }

    private void handleCommandBye() {
        System.out.println("Bye bye");
        System.out.println("---------------");
    }

    private void handleCommandInvalid() {
        System.out.println("Invalid Command Received! Have an exception handler here later?");
        System.out.println("---------------");
    }
}
