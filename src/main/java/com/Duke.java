package com;

import java.net.URISyntaxException;
import java.util.Scanner;

import com.command.Command;
import com.command.CommandHandler;
import com.exceptions.FinishAppException;
import com.exceptions.InvalidArgumentException;
import com.exceptions.InvalidCommandException;
import com.parser.CommandParser;




public class Duke {
    static final String LOGO =
        " ____        _        \n"
        + "|  _ \\ _   _| | _____ \n"
        + "| | | | | | | |/ / _ \\\n"
        + "| |_| | |_| |   <  __/\n"
        + "|____/ \\__,_|_|\\_\\___|\n";
    static final String GREETING =
        "____________________________________________________________\n"
        + "Hello! I'm Duke\n"
        + "What can I do for you?\n";


    /** Main */
    public static void main(String[] args) {
        System.out.println("Hello from\n" + LOGO + GREETING);
        Scanner scanner = new Scanner(System.in); // Create a Scanner object
        try {
            CommandHandler commandHandler = new CommandHandler();
            CommandParser commandParser = new CommandParser();
            while (true) {
                try {
                    String input = scanner.nextLine();
                    Command cmd = commandParser.parse(input);
                    if (cmd != null) {
                        commandHandler.handlerCommand(cmd);
                    }
                } catch (InvalidCommandException e) {
                    System.out.println("Invalid command!");
                } catch (InvalidArgumentException e) {
                    System.out.println("Invalid command argument!");
                } catch (NumberFormatException e) {
                    System.out.println("Invalid integer value!");
                } catch (FinishAppException e) {
                    System.exit(0);
                }
            }
        } catch (URISyntaxException e) {
            System.out.println("Parser received invalid input file path!");
        } finally {
            scanner.close();
        }
    }
}


