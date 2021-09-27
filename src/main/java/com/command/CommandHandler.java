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
        this.taskManager = new TaskManager(ui);
        this.ui = ui;
        this.storage = storage;
        this.storage.assignTaskManager(taskManager);
    }

    /**
     * Main entry for handling command
     * @param command
     * @return return the response in string to be rendered on the ui
     */
    public String handlerCommand(Command command) throws NumberFormatException, FinishAppException {
        command.execute(taskManager, storage, ui);
        return "";
    }



}
