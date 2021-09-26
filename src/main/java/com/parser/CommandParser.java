package com.parser;

import java.io.File;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import com.command.AddCommand;
import com.command.ByeCommand;
import com.command.Command;
import com.command.CommandType;
import com.command.DeleteCommand;
import com.command.ListCommand;
import com.command.MarkCommand;
import com.command.SaveCommand;
import com.dopsun.chatbot.cli.Argument;
import com.dopsun.chatbot.cli.ParseResult;
import com.dopsun.chatbot.cli.Parser;
import com.dopsun.chatbot.cli.ParserBuilder;
import com.dopsun.chatbot.cli.input.CommandSet;
import com.dopsun.chatbot.cli.input.FileCommandSet;
import com.dopsun.chatbot.cli.input.FileTrainingSet;
import com.dopsun.chatbot.cli.input.TrainingSet;
import com.exceptions.InvalidArgumentException;
import com.exceptions.InvalidCommandException;
import com.task.TaskType;



public class CommandParser {

    private static Parser parser;

    /**
     * Constructor. Initialise internal parser
     */
    public CommandParser() throws URISyntaxException {
        prepareParser();
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
            default:
                System.out.println("Unrecognised simple command!");
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
            default:
                throw new InvalidCommandException();
            }
        } else {
            throw new InvalidCommandException();
        }
    }


    private void prepareParser() throws URISyntaxException {
        // File.separator
        String commandSetPath = System.getProperty("user.dir") + "/data/input/command-data.properties";
        String trainingPath = System.getProperty("user.dir") + "/data/input/training-data.yml";
        Path csPath = Paths.get(new File(commandSetPath).toURI());
        CommandSet commandSet = new FileCommandSet(csPath);
        Path tsPath = Paths.get(new File(trainingPath).toURI());
        TrainingSet trainingSet = new FileTrainingSet(tsPath);

        ParserBuilder parserBuilder = Parser.newBuilder();
        parserBuilder.addCommandSet(commandSet);
        parserBuilder.addTrainingSet(trainingSet);
        // parserBuilder.setTracer(System.out::println);
        parser = parserBuilder.build();

    }


    private Command parseTodoArgs(List<Argument> commandArgs) {
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
                System.out.println("Unrecognised argument: " + argName);
                break;
            }
        }
        return command;
    }

    private Command parseDeadlineArgs(List<Argument> commandArgs) throws InvalidArgumentException {
        Command command = new AddCommand();
        command.setTaskType(TaskType.DEADLINE);
        for (Argument a : commandArgs) {
            String argName = a.name();
            String argVal = a.value().get();
            switch (argName) {
            case "task-hint":
                command.setTaskDescription(argVal);
                break;
            case "date-hint":
                command.setTimeInfo(argVal);
                break;
            default:
                throw new InvalidArgumentException();
            }
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
                    command.setTimeInfo(argVal);
                    break;
                default:
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
                throw new InvalidArgumentException();
            }
        }
        return command;
    }
}
