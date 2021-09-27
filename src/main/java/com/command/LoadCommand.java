package com.command;

import com.storage.Storage;
import com.task.TaskManager;
import com.ui.Ui;

public class LoadCommand extends Command {

    public LoadCommand() {
        super(CommandType.LOAD);
    }

    /**
     * Implementing abstract method for polymorphism
     */
    public String execute(TaskManager taskManager, Storage storage, Ui ui) {
        if (storage.loadTasks()) {
            return "Loaded tasks: " + getTaskDescription();
        }
        return "Failed to load tasks!";
    }
}

