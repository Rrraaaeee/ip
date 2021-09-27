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
        taskManager.deleteTask(taskId);

        ui.showText("Deleted task" + taskId);
    }

}


