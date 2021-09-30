package com.command;

import com.storage.Storage;
import com.task.TaskManager;
import com.ui.Ui;

public class DeleteCommand extends Command {

    public DeleteCommand() {
        super(CommandType.DELETE);
    }

    /**
     * Implementing abstract method for polymorphism
     */
    public void execute(TaskManager taskManager, Storage storage, Ui ui) {
        Integer taskId = Integer.parseInt(getTaskDescription().strip());

        ui.showText("I have helped you delted task: \n" + taskManager.deleteTask(taskId));
        ui.showSeperator();

    }

}


