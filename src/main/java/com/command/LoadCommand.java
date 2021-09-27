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
    public void execute(TaskManager taskManager, Storage storage, Ui ui) {
        if (storage.loadTasks()) {
            ui.showText("Loaded tasks: " + getTaskDescription());
        }
    }

}

