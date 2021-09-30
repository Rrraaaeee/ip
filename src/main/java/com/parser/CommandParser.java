package com.parser;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import com.command.AddCommand;
import com.command.ByeCommand;
import com.command.Command;
import com.command.CommandType;
import com.command.DeleteCommand;
import com.command.FindCommand;
import com.command.HelpCommand;
import com.command.ListCommand;
import com.command.MarkCommand;
import com.command.SaveCommand;
import com.dopsun.chatbot.cli.Argument;
import com.dopsun.chatbot.cli.ParseResult;
import com.dopsun.chatbot.cli.Parser;
import com.exceptions.InvalidArgumentException;
import com.exceptions.InvalidCommandException;
import com.task.TaskType;
import com.time.Time;
import com.ui.Ui;
import com.util.Util;


public class CommandParser extends ParserBase {

    private TimeParser timeParser;
    private Parser parser;
    private Ui ui;

    /**
     * Constructor. Initialise internal parser
     */
    public CommandParser(Ui ui) throws URISyntaxException {
        super();

        // File.separator
        // String commandSetPath = System.getProperty("user.dir") + "/data/input/command-data.properties";
        // String trainingPath = System.getProperty("user.dir") + "/data/input/training-data.yml";
        this.ui = ui;
        try {
            InputStream commandSetInputStream = this.getClass().getResourceAsStream("/input/command-data.properties");

            File commandSetTmpFile = Util.inputStreamtoTmpFile(commandSetInputStream,
                    System.getProperty("user.dir") + "/tmp", "/tmp_file_command.txt");

            InputStream trainingPathInputStream = this.getClass().getResourceAsStream("/input/training-data.yml");
            File trainingTmpFile = Util.inputStreamtoTmpFile(trainingPathInputStream,
                    System.getProperty("user.dir") + "/tmp", "/tmp_file_training.txt");

            parser = prepareParser(commandSetTmpFile.getPath(), trainingTmpFile.getPath());

            timeParser = new TimeParser(ui);
        } catch (IOException e) {
            ui.showText("Error encountered when creating temp file: "
                    + System.getProperty("user.dir") + "/tmp" + "/tmp_file_command.txt" + " or "
                    + System.getProperty("user.dir") + "/tmp" + "/tmp_file_training.txt");
        }
    }

    /**
     * API to parse a command in string
     * @param input command to be parsed
     * @return a command object, to be passed into commandhandler
     */
    public Command parse(String input) throws InvalidCommandException, InvalidArgumentException {
        if (input.split(" ").length < 2) {
            return parseSimpleInput(input);
        } else {
            return parseComplexInput(input);
        }
    }

    private Command parseSimpleInput(String input) throws InvalidCommandException {

        CommandType commandType = CommandType.getCommandTypeByStr(input);
        if (commandType == CommandType.INVALID || !CommandType.isSimpleCommand(commandType)) {
            throw new InvalidCommandException();
        } else {
            switch (commandType) {
            case LIST:
                return new ListCommand();
            case SAVE:
                return new SaveCommand();
            case BYE:
                return new ByeCommand();
            case HELP:
                return new HelpCommand();
            default:
                ui.showText("Unrecognised simple command: " + CommandType.getCommandStrByType(commandType));
                return null;
            }
        }

    }

    private Command parseComplexInput(String input) throws InvalidCommandException, InvalidArgumentException {
        Optional<ParseResult> optResult = parser.tryParse(input);
        if (optResult.isPresent()) {
            var result = optResult.get();
            String commandType = result.allCommands().get(0).name();
            // String template = res.allCommands().get(0).template();
            List<Argument> commandArgs = result.allCommands().get(0).arguments();
            switch (commandType) {
            case "todo":
                return parseTodoArgs(commandArgs);
            case "deadline":
                return parseDeadlineArgs(commandArgs);
            case "event":
                return parseEventArgs(commandArgs);
            case "done":
                return parseDoneArgs(commandArgs);
            case "delete":
                return parseDeleteArgs(commandArgs);
            case "find":
                return parseFindArgs(commandArgs);
            default:
                throw new InvalidCommandException();
            }
        } else {
            throw new InvalidCommandException();
        }
    }


    private Command parseTodoArgs(List<Argument> commandArgs) throws InvalidArgumentException {
        Command command = new AddCommand();
        command.setTaskType(TaskType.TODO);
        for (Argument a : commandArgs) {
            String argName = a.name();
            String argVal = a.value().get();
            switch (argName) {
            case "task-hint":
                command.setTaskDescription(argVal);
                break;
            /*
            case "date-hint":
                command.setTimeInfo(argVal);
                break;
            */
            default:
                ui.showText("Unrecognised argument for todo: " + argName);
                throw new InvalidArgumentException();
            }
        }
        return command;
    }

    private Command parseDeadlineArgs(List<Argument> commandArgs) throws InvalidArgumentException {
        Command command = new AddCommand();
        command.setTaskType(TaskType.DEADLINE);
        try {
            for (Argument a : commandArgs) {
                String argName = a.name();
                String argVal = a.value().get();
                switch (argName) {
                case "task-hint":
                    command.setTaskDescription(argVal);
                    break;
                case "date-hint":
                    Time time = timeParser.parse(argVal);
                    command.setTimeInfo(time);
                    break;
                default:
                    ui.showText("Unrecognised argument for deadline: " + argName);
                    throw new InvalidArgumentException();
                }
            }
        } catch (NoSuchElementException e) {
            throw new InvalidArgumentException();
        }
        return command;
    }

    private Command parseEventArgs(List<Argument> commandArgs) throws InvalidArgumentException {
        Command command = new AddCommand();
        command.setTaskType(TaskType.EVENT);
        try {
            for (Argument a : commandArgs) {
                String argName = a.name();
                String argVal = a.value().get();
                switch (argName) {
                case "task-hint":
                    command.setTaskDescription(argVal);
                    break;
                case "date-hint":
                    Time time = timeParser.parse(argVal);
                    command.setTimeInfo(time);
                    break;
                default:
                    ui.showText("Unrecognised argument for event: " + argName);
                    throw new InvalidArgumentException();
                }
            }
        } catch (NoSuchElementException e) {
            throw new InvalidArgumentException();
        }
        return command;
    }

    private Command parseDoneArgs(List<Argument> commandArgs) throws InvalidArgumentException {
        Command command = new MarkCommand();
        for (Argument a : commandArgs) {
            String argName = a.name();
            String argVal = a.value().get();
            switch (argName) {
            case "list-idx-hint":
                command.setTaskDescription(argVal);
                break;
            default:
                ui.showText("Unrecognised argument for mark: " + argName);
                throw new InvalidArgumentException();
            }
        }
        return command;
    }
    private Command parseDeleteArgs(List<Argument> commandArgs) throws InvalidArgumentException {
        Command command = new DeleteCommand();
        command.setCommandType(CommandType.DELETE);
        for (Argument a : commandArgs) {
            String argName = a.name();
            String argVal = a.value().get();
            switch (argName) {
            case "list-idx-hint":
                command.setTaskDescription(argVal);
                break;
            default:
                ui.showText("Unrecognised argument for delete: " + argName);
                throw new InvalidArgumentException();
            }
        }
        return command;
    }

    private Command parseFindArgs(List<Argument> commandArgs) throws InvalidArgumentException {
        Command command = new FindCommand();
        command.setCommandType(CommandType.FIND);
        for (Argument a : commandArgs) {
            String argName = a.name();
            String argVal = a.value().get();
            switch (argName) {
            case "task-hint":
                command.setTaskDescription(argVal);
                break;
            default:
                ui.showText("Unrecognised argument for find: " + argName);
                throw new InvalidArgumentException();
            }
        }
        return command;
    }
}
