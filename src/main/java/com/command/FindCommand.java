package com.command;

import com.storage.Storage;
import com.task.TaskBase;
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
        for (TaskBase task : taskManager.findTasks(getTaskDescription())) {
            ui.showText("Found: " + task.toString());
        }
        ui.showSeperator();
    }

}
