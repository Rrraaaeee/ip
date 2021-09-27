package com;

import java.net.URISyntaxException;
import java.util.Scanner;
import javafx.stage.Stage;
import javafx.application.Application;

import com.command.Command;
import com.command.CommandHandler;
import com.exceptions.FinishAppException;
import com.exceptions.InvalidArgumentException;
import com.exceptions.InvalidCommandException;
import com.parser.CommandParser;
import com.storage.Storage;
import com.ui.Ui;


// https://sticker.weixin.qq.com/cgi-bin/mmemoticon-bin/emoticonview?oper=single&t=shop/detail&productid=aL2PCfwK/89qO7sF6/+I+UDhfwEjhec2ZNvdnLLJRd/MwZDDtg4gTJsw5pktwTOzVdBiBxVa/GhcN8gdDisVW0ACS6fFvJnD++CRbZt0uERg=
public class Duke extends Application {

    private static final String storagePath = System.getProperty("user.dir") + "/tmp/storage.txt";

    // Commandline components
    private Scanner scanner;
    private Ui ui;
    private CommandHandler commandHandler;
    private CommandParser commandParser;
    private Storage storage;


    /**
     * Default constructor needed to instantiate javafx
     **/
    public Duke() {
        this(storagePath);
    }

    /**
     * Constructor that initialises all internal handler objects
     * such as scanner, ui, parser and commandHandler
     **/
    public Duke(String storagePath) {
        ui = new Ui(System.out);
        scanner = new Scanner(System.in); // Create a Scanner object
        storage = new Storage(storagePath);
        commandHandler = new CommandHandler(storage, ui);
        try {
            commandParser = new CommandParser();
        } catch (URISyntaxException e) {
            ui.showInvalidFilePathError();
        }
    }

    /**
     * Duke javafx entry
     */
    @Override
    public void start(Stage stage) {
        ui.showText("[TODO] Dont forget to clean up generic exception error");
        ui.showText("[TODO] Use File.seperator so that path works on both mac and window");
        ui.showText("[TODO] How to integrate files into single jar??");

        // pass duke into ui so ui can access duke backend
        ui.create(stage, this);
        ui.greet();
        ui.showUi();
    }

    /**
     * This is the callback UI interact with backend
     * Input: User input from the UI
     * Return: Response string to be rendered in the app
     */
    public String getResponse(String input) {
        try {
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



        return "Duke heard: " + input;
    }



}


