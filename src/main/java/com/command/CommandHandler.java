package com.command;

import com.exceptions.FinishAppException;
import com.storage.Storage;
import com.task.TaskBase;
import com.task.TaskFactory;
import com.task.TaskManager;

public class CommandHandler {

    private TaskManager taskManager;
    private TaskFactory taskFactory;
    private Storage storage;

    /**
     * Constructor to create taskManager and taskFactory
     * and fully initialise storage unit
     **/
    public CommandHandler(Storage storage) {
        this.taskFactory = new TaskFactory();
        this.taskManager = new TaskManager();
        this.storage = storage;
        this.storage.assignTaskManager(taskManager);
        this.storage.assignTaskFactory(taskFactory);
    }

    /**
     * Handle command
     * @param command
     */
    public void handlerCommand(Command command) throws NumberFormatException, FinishAppException {
        CommandType commandType = command.getCommandType();
        System.out.println("____________________________________________________________");
        switch (commandType) {
        case LIST:
            handleCommandList();
            break;
        case ADD:
            handleCommandAdd(command);
            break;
        case DONE:
            handleCommandDone(command);
            break;
        case DELETE:
            handleCommandDelete(command);
            break;
        case FIND:
            handleCommandFind();
            break;
        case BYE:
            handleCommandBye();
            break;
        case SAVE:
            handleCommandSave();
            break;
        case LOAD:
            handleCommandLoad();
            break;
        case INVALID:
            handleCommandInvalid();
            break;
        default:
            handleCommandInvalid();
            break;
        }
        System.out.println("____________________________________________________________");
    }

    private void handleCommandList() {
        System.out.println("list");
        System.out.println("---------------");
        taskManager.listAllTasks();
    }

    private void handleCommandAdd(Command command) {
        System.out.println("added: " + command.getTaskDescription());
        System.out.println("---------------");
        TaskBase task = taskFactory.makeTask(command);
        if (task != null) {
            taskManager.addTask(task);
        }
    }

    protected void handleCommandDone(Command command) throws NumberFormatException {
        Integer taskId = Integer.parseInt(command.getTaskDescription().strip());
        System.out.println("Marking task " + taskId + " as done");
        System.out.println("---------------");
        taskManager.markTaskDone(taskId);
    }


    private void handleCommandDelete(Command command) {
        Integer taskId = Integer.parseInt(command.getTaskDescription().strip());
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
        storage.saveTasks();
    }

    private void handleCommandLoad() {
        System.out.println("load");
        System.out.println("---------------");
        storage.loadTasks();
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
