package com;

import java.net.URISyntaxException;

import com.command.Command;
import com.command.CommandHandler;
import com.exceptions.FinishAppException;
import com.exceptions.InvalidArgumentException;
import com.exceptions.InvalidCommandException;
import com.parser.CommandParser;
import com.storage.Storage;
import com.ui.Ui;




public class Duke {
    private Ui ui;
    private CommandHandler commandHandler;
    private CommandParser commandParser;
    private Storage storage;

    /**
     * Constructor that initialises all internal handler objects
     * such as scanner, ui, parser and commandHandler
     **/
    public Duke(String storagePath) {
        ui = new Ui(System.in, System.out);
        storage = new Storage(storagePath, ui);
        commandHandler = new CommandHandler(storage, ui);

        try {
            commandParser = new CommandParser(ui);
        } catch (URISyntaxException e) {
            ui.showInvalidFilePathError();
        }
    }

    /**
     * Duke entry
     */
    public void run() {
        startUp();
        ui.showSeperator();
        // event loop
        while (true) {
            nextEvent();
        }
    }

    private void startUp() {
        ui.showLogo();
        ui.showGreetingMessage();
        ui.showSeperator();

        ui.showText("Loading tasks from storage ...");
        ui.showSeperator();;
        storage.loadTasks();

    }
    private void nextEvent() {
        try {
            ui.showPrompt();
            String input = ui.getInput();
            ui.showSeperator();
            Command command = commandParser.parse(input);
            if (command != null) {
                commandHandler.handlerCommand(command);
            }
        } catch (InvalidCommandException e) {
            ui.showInvalidCommandError();
        } catch (InvalidArgumentException e) {
            ui.showInvalidCommandArgumentError();
        } catch (NumberFormatException e) {
            ui.showInvalidNumberError();
        } catch (FinishAppException e) {
            ui.showBye();
            ui.closeStreams();
            System.exit(0);
        }
    }


    /**
     * Main entry point
     * */
    public static void main(String[] args) {
        String storagePath = System.getProperty("user.dir") + "/tmp/storage.txt";
        new Duke(storagePath).run();
    }

}


