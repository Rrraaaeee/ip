package com.command;

import com.storage.Storage;
import com.task.TaskManager;
import com.ui.Ui;

public class FindCommand extends Command {

    public FindCommand() {
        super(CommandType.FIND);
    }

    /**
     * Implementing abstract method for polymorphism
     */
    public void execute(TaskManager taskManager, Storage storage, Ui ui) {
        ui.showText("Find is not yet implemented");
        ui.showText("Found: " + getTaskDescription());
        ui.showSeperator();
    }

}
