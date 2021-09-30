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
        ui.showText("[WARNING] Loading tasks without saving will lose your current data!"
                + "Do you want to continue? [y/n]");
        while (true) {
            String response = ui.getInput();
            response = response.toLowerCase().strip();
            if (response.equals("y")) {
                break;
            } else if (response.equals("n")) {
                ui.showText("Load not performed.");
                ui.showSeperator();
                return;
            } else {
                ui.showText("Please respond with either y or n.");
                ui.showSeperator();
            }
        }
        ui.showText("Loaded tasks: ");
        if (storage.loadTasks()) {
            ui.showSeperator();
        }
    }

}

