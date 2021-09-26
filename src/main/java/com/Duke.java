package com;

import java.net.URISyntaxException;
import java.util.Scanner;

import com.command.Command;
import com.command.CommandHandler;
import com.exceptions.FinishAppException;
import com.exceptions.InvalidArgumentException;
import com.exceptions.InvalidCommandException;
import com.parser.CommandParser;
import com.storage.Storage;
import com.ui.Ui;




public class Duke {
    private Scanner scanner;
    private Ui ui;
    private CommandHandler commandHandler;
    private CommandParser commandParser;
    private Storage storage;

    /**
     * Constructor that initialises all internal handler objects
     * such as scanner, ui, parser and commandHandler
     **/
    public Duke(String storagePath) {
        ui = new Ui(System.out);
        scanner = new Scanner(System.in); // Create a Scanner object
        storage = new Storage(storagePath);
        commandHandler = new CommandHandler(storage);

        try {
            commandParser = new CommandParser();
        } catch (URISyntaxException e) {
            ui.showInvalidFilePathError();
        }
    }

    /**
     * Duke entry
     */
    public void run() {
        startUp();
        // event loop
        while (true) {
            nextEvent();
        }
    }

    private void startUp() {
        ui.showGreetingMessage();
        storage.loadTasks();

    }
    private void nextEvent() {
        try {
            String input = scanner.nextLine();
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
            scanner.close();
            System.exit(0);
        }
    }


    /**
     * Main entry point
     * */
    public static void main(String[] args) {
        String storagePath = System.getProperty("user.dir") + "/data/storage.txt";
        new Duke(storagePath).run();
    }

}


