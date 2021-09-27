package com.command;

import com.storage.Storage;
import com.task.TaskManager;
import com.ui.Ui;

public class InvalidCommand extends Command {

    public InvalidCommand() {
        super(CommandType.INVALID);
    }

    /**
     * Implementing abstract method for polymorphism
     */
    public String execute(TaskManager taskManager, Storage storage, Ui ui) {
        return "Invalid Command Received!";
    }

}


