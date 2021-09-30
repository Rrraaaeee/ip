package com.command;

import com.exceptions.FinishAppException;
import com.storage.Storage;
import com.task.TaskManager;
import com.ui.Ui;

public class ByeCommand extends Command {

    public ByeCommand() {
        super(CommandType.BYE);
    }

    /**
     * Implementing abstract method for polymorphism
     */
    public void execute(TaskManager taskManager, Storage storage, Ui ui) throws FinishAppException {
        ui.showText("Do you want to save your updated tasks? [y/n]");
        while (true) {
            ui.showPrompt();
            String response = ui.getInput();
            response = response.toLowerCase().strip();
            if (response.equals("y")) {
                storage.saveTasks();
                ui.showText("Saved tasks.");
                break;
            } else if (response.equals("n")) {
                ui.showText("New tasks are not saved.");
                break;
            } else {
                ui.showText("Please respond with either y or n.");
                ui.showSeperator();
            }
        }

        throw new FinishAppException();
    }

}


