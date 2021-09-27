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
    public String execute(TaskManager taskManager, Storage storage, Ui ui) {
        String response = "";
        for (TaskBase task : taskManager.findTasks(getTaskDescription())) {
            response += "Found: " + task.toString() + "\n";
        }
        return response;
    }

}
