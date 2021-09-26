package com.command;

import com.exceptions.FinishAppException;
import com.storage.Storage;
import com.task.TaskManager;
import com.ui.Ui;

public class CommandHandler {

    private TaskManager taskManager;
    private Storage storage;
    private Ui ui;


    /**
     * Constructor to create taskManager and taskFactory
     * and fully initialise storage unit
     **/
    public CommandHandler(Storage storage, Ui ui) {
        this.taskManager = new TaskManager();
        this.ui = ui;
        this.storage = storage;
        this.storage.assignTaskManager(taskManager);
    }

    /**
     * Handle command
     * @param command
     */
    public void handlerCommand(Command command) throws NumberFormatException, FinishAppException {
        command.execute(taskManager, storage, ui);
    }



}
