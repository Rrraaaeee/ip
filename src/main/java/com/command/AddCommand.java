package com.command;

import com.storage.Storage;
import com.task.TaskBase;
import com.task.TaskManager;
import com.ui.Ui;

public class AddCommand extends Command {

    public AddCommand() {
        super(CommandType.ADD);
    }

    /**
     * Implementing abstract method for polymorphism
     */
    public void execute(TaskManager taskManager, Storage storage, Ui ui) {
        TaskBase task = taskManager.createTask(this);
        taskManager.addTask(task);

        //ui.showText("I have added Todo: " + getTaskDescription());
        ui.showText("I have added task: " + task);
        ui.showSeperator();
    }

}


