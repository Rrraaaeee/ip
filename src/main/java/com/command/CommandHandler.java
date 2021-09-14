package com.command;

import java.io.FileNotFoundException;

import com.exceptions.FinishAppException;
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
        taskFactory = new TaskFactory();
        taskManager = new TaskManager(taskFactory);
    }

    /**
     * Handle command
     * @param cmd
     */
    public void handlerCommand(Command cmd) throws NumberFormatException, FinishAppException {
        CommandType cmdType = cmd.getCommandType();
        System.out.println("____________________________________________________________");
        if (cmdType == CommandType.LIST) {
            handleCommandList();
        } else if (cmdType == CommandType.ADD) {
            handleCommandAdd(cmd);
        } else if (cmdType == CommandType.DONE) {
            handleCommandDone(cmd);
        } else if (cmdType == CommandType.DELETE) {
            handleCommandDelete(cmd);
        } else if (cmdType == CommandType.FIND) {
            handleCommandFind();
        } else if (cmdType == CommandType.BYE) {
            handleCommandBye();
        } else if (cmdType == CommandType.SAVE) {
            handleCommandSave();
        } else if (cmdType == CommandType.LOAD) {
            handleCommandLoad();
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

    protected void handleCommandDone(Command cmd) throws NumberFormatException {
        Integer taskId = Integer.parseInt(cmd.getTaskDescription().strip());
        System.out.println("Marking task " + taskId + " as done");
        System.out.println("---------------");
        taskManager.markTaskDone(taskId);
    }


    private void handleCommandDelete(Command cmd) {
        Integer taskId = Integer.parseInt(cmd.getTaskDescription().strip());
        System.out.println("Deleting task " + taskId);
        System.out.println("---------------");
        taskManager.deleteTask(taskId);
    }

    private void handleCommandFind() {
        System.out.println("find");
        System.out.println("---------------");
    }

    private void handleCommandSave() {
        System.out.println("save");
        System.out.println("---------------");
        taskManager.saveTasks();
    }

    private void handleCommandLoad() {
        System.out.println("load");
        System.out.println("---------------");
        taskManager.loadTasks();
    }

    private void handleCommandBye() throws FinishAppException {
        System.out.println("Bye bye");
        System.out.println("---------------");
        throw new FinishAppException();
    }

    private void handleCommandInvalid() {
        System.out.println("Invalid Command Received! Have an exception handler here later?");
        System.out.println("---------------");
    }
}
