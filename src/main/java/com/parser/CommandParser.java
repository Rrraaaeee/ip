package com.parser;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import com.command.Command;
import com.command.CommandType;
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
        Command command = new Command();
        CommandType commandType = CommandType.getCommandTypeByStr(input);
        if (commandType == CommandType.INVALID || !CommandType.isSimpleCommand(commandType)) {
            throw new InvalidCommandException();
        } else {
            command.setCommandType(commandType);
            return command;
        }
    }

    private Command parseComplexInput(String input) throws InvalidCommandException, InvalidArgumentException {
        Optional<ParseResult> optResult = parser.tryParse(input);
        if (optResult.isPresent()) {
            Command command = new Command();
            var result = optResult.get();
            String commandType = result.allCommands().get(0).name();
            // String template = res.allCommands().get(0).template();
            List<Argument> commandArgs = result.allCommands().get(0).arguments();

            switch (commandType) {
            case "todo":
                parseTodoArgs(command, commandArgs);
                break;
            case "deadline":
                parseDeadlineArgs(command, commandArgs);
                break;
            case "event":
                parseEventArgs(command, commandArgs);
                break;
            case "done":
                parseDoneArgs(command, commandArgs);
                break;
            case "delete":
                parseDeleteArgs(command, commandArgs);
                break;
            default:
                throw new InvalidCommandException();
            }
            return command;
        } else {
            throw new InvalidCommandException();
        }
    }


    private void prepareParser() throws URISyntaxException {
        URL csUrl = ClassLoader.getSystemResource(
                "/users/raeee/work/ip/src/main/resources/input/command-data.properties");
        // Path csPath = Paths.get(csUrl.toURI());
        Path csPath = Paths.get("/users/raeee/work/ip/src/main/resources/input/command-data.properties");

        CommandSet commandSet = new FileCommandSet(csPath);

        URL tsUrl = ClassLoader.getSystemResource(
                "/Users/raeee/work/ip/src/main/resources/input/training-data.yml");
        // Path tsPath = Paths.get(tsUrl.toURI());
        Path tsPath = Paths.get("/Users/raeee/work/ip/src/main/resources/input/training-data.yml");
        TrainingSet trainingSet = new FileTrainingSet(tsPath);

        ParserBuilder parserBuilder = Parser.newBuilder();
        parserBuilder.addCommandSet(commandSet);
        parserBuilder.addTrainingSet(trainingSet);

        parserBuilder.setTracer(System.out::println);
        parser = parserBuilder.build();
    }


    private void parseTodoArgs(Command command, List<Argument> commandArgs) {
        command.setCommandType(CommandType.ADD);
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
    }

    private void parseDeadlineArgs(Command command, List<Argument> commandArgs) throws InvalidArgumentException {
        command.setCommandType(CommandType.ADD);
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
    }

    private void parseEventArgs(Command command, List<Argument> commandArgs) throws InvalidArgumentException {
        command.setCommandType(CommandType.ADD);
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
    }

    private void parseDoneArgs(Command command, List<Argument> commandArgs) throws InvalidArgumentException {
        command.setCommandType(CommandType.DONE);
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
    }
    private void parseDeleteArgs(Command command, List<Argument> commandArgs) throws InvalidArgumentException {
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
    }




}
