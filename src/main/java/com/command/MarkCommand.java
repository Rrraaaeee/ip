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
    public void execute(TaskManager taskManager, Storage storage, Ui ui) {
        Integer taskId = Integer.parseInt(this.getTaskDescription().strip());
        Integer taskListSize = taskManager.getTaskListSize();
        if (taskId > taskListSize || taskId < 1) {
            ui.showText("The task ID you just keyed in is invalid");
            return;
        } else {
            ui.showText("I have helped you marked your task: \n" + taskManager.markTaskDone(taskId));
            ui.showSeperator();
        }
    }
}


