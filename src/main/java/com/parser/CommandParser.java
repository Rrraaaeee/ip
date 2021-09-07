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
        Command cmd = new Command();
        CommandType cmdType = CommandType.getCommandTypeByStr(input);
        if (cmdType == CommandType.INVALID || !CommandType.isSimpleCommand(cmdType)) {
            throw new InvalidCommandException();
        } else {
            cmd.setCommandType(cmdType);
            return cmd;
        }
    }

    private Command parseComplexInput(String input) throws InvalidCommandException, InvalidArgumentException {
        Optional<ParseResult> optResult = parser.tryParse(input);
        if (optResult.isPresent()) {
            Command cmd = new Command();
            var result = optResult.get();
            String commandType = result.allCommands().get(0).name();
            // String template = res.allCommands().get(0).template();
            List<Argument> cmdArgs = result.allCommands().get(0).arguments();

            switch (commandType) {
            case "todo":
                parseTodoArgs(cmd, cmdArgs);
                break;
            case "deadline":
                parseDeadlineArgs(cmd, cmdArgs);
                break;
            case "event":
                parseEventArgs(cmd, cmdArgs);
                break;
            case "done":
                parseDoneArgs(cmd, cmdArgs);
                break;
            case "delete":
                parseDeleteArgs(cmd, cmdArgs);
                break;
            default:
                throw new InvalidCommandException();
            }
            return cmd;
        } else {
            throw new InvalidCommandException();
        }
    }


    private void prepareParser() throws URISyntaxException {
        URL csUrl = ClassLoader.getSystemResource("input/command-data.properties");
        Path csPath = Paths.get(csUrl.toURI());

        CommandSet commandSet = new FileCommandSet(csPath);

        URL tsUrl = ClassLoader.getSystemResource("input/training-data.yml");
        Path tsPath = Paths.get(tsUrl.toURI());
        TrainingSet trainingSet = new FileTrainingSet(tsPath);

        ParserBuilder parserBuilder = Parser.newBuilder();
        parserBuilder.addCommandSet(commandSet);
        parserBuilder.addTrainingSet(trainingSet);

        parserBuilder.setTracer(System.out::println);
        parser = parserBuilder.build();
    }


    private void parseTodoArgs(Command cmd, List<Argument> cmdArgs) {
        cmd.setCommandType(CommandType.ADD);
        cmd.setTaskType(TaskType.TODO);
        for (Argument a : cmdArgs) {
            String argName = a.name();
            String argVal = a.value().get();
            switch (argName) {
            case "task-hint":
                cmd.setTaskDescription(argVal);
                break;
            /*
            case "date-hint":
                cmd.setTimeInfo(argVal);
                break;
            */
            default:
                System.out.println("Unrecognised argument: " + argName);
                break;
            }
        }
    }

    private void parseDeadlineArgs(Command cmd, List<Argument> cmdArgs) throws InvalidArgumentException {
        cmd.setCommandType(CommandType.ADD);
        cmd.setTaskType(TaskType.DEADLINE);
        for (Argument a : cmdArgs) {
            String argName = a.name();
            String argVal = a.value().get();
            switch (argName) {
            case "task-hint":
                cmd.setTaskDescription(argVal);
                break;
            case "date-hint":
                cmd.setTimeInfo(argVal);
                break;
            default:
                throw new InvalidArgumentException();
            }
        }
    }

    private void parseEventArgs(Command cmd, List<Argument> cmdArgs) throws InvalidArgumentException {
        cmd.setCommandType(CommandType.ADD);
        cmd.setTaskType(TaskType.EVENT);
        try {
            for (Argument a : cmdArgs) {
                String argName = a.name();
                String argVal = a.value().get();
                switch (argName) {
                case "task-hint":
                    cmd.setTaskDescription(argVal);
                    break;
                case "date-hint":
                    cmd.setTimeInfo(argVal);
                    break;
                default:
                    throw new InvalidArgumentException();
                }
            }
        } catch (NoSuchElementException e) {
            throw new InvalidArgumentException();
        }
    }

    private void parseDoneArgs(Command cmd, List<Argument> cmdArgs) throws InvalidArgumentException {
        cmd.setCommandType(CommandType.DONE);
        for (Argument a : cmdArgs) {
            String argName = a.name();
            String argVal = a.value().get();
            switch (argName) {
            case "list-idx-hint":
                cmd.setTaskDescription(argVal);
                break;
            default:
                throw new InvalidArgumentException();
            }
        }
    }
    private void parseDeleteArgs(Command cmd, List<Argument> cmdArgs) throws InvalidArgumentException {
        cmd.setCommandType(CommandType.DELETE);
        for (Argument a : cmdArgs) {
            String argName = a.name();
            String argVal = a.value().get();
            switch (argName) {
            case "list-idx-hint":
                cmd.setTaskDescription(argVal);
                break;
            default:
                throw new InvalidArgumentException();
            }
        }
    }




}
