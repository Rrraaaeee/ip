package com.command;

import com.storage.Storage;
import com.task.TaskManager;
import com.ui.Ui;

public class MarkCommand extends Command {

    public MarkCommand() {
        super(CommandType.DONE);
    }

    /**
     * Implementing abstract method for polymorphism
     */
    public String execute(TaskManager taskManager, Storage storage, Ui ui) {
        Integer taskId = Integer.parseInt(this.getTaskDescription().strip());
        taskManager.markTaskDone(taskId);
        return "Marked task: " + taskId;
    }

}


