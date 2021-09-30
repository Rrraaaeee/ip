package com.command;

import com.storage.Storage;
import com.task.TaskManager;
import com.ui.Ui;

public class ListCommand extends Command {

    public ListCommand() {
        super(CommandType.LIST);
    }

    /**
     * Implementing abstract method for polymorphism
     */
    public void execute(TaskManager taskManager, Storage storage, Ui ui) {

        ui.showText("Here are your available tasks:\n" + taskManager.listAllTasks());
        ui.showSeperator();

    }

}


