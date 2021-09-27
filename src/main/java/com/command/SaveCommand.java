package com.command;

import com.storage.Storage;
import com.task.TaskManager;
import com.ui.Ui;

public class SaveCommand extends Command {

    public SaveCommand() {
        super(CommandType.SAVE);
    }

    /**
     * Implementing abstract method for polymorphism
     */
    public String execute(TaskManager taskManager, Storage storage, Ui ui) {
        storage.saveTasks();
        return "Saved tasks!";
    }

}
