package com.command;

import com.storage.Storage;
import com.task.TaskManager;
import com.ui.Ui;

public class HelpCommand extends Command {

    private static final String HELP = "========== HELP =========\n"
                                     + "(add) todo [task name]\n"
                                     + "(add) deadline [task name] by [time]\n"
                                     + "(add) event [task name] on [time]\n"
                                     + "find [task name]\n"
                                     + "mark [task id]\n"
                                     + "delete [task id]\n"
                                     + "list\n"
                                     + "load\n"
                                     + "save\n"
                                     + "bye";


    public HelpCommand() {
        super(CommandType.HELP);
    }

    /**
     * Implementing abstract method for polymorphism
     */
    public void execute(TaskManager taskManager, Storage storage, Ui ui) {
        ui.showText(HELP);
    }

}


