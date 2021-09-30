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
        throw new FinishAppException();
    }

}


