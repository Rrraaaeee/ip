package com.parser;

import com.dopsun.chatbot.cli.*;
import com.dopsun.chatbot.cli.input.*;
import com.task.TaskType;
import com.command.*;
import com.command.Command;

import java.nio.file.*;
import java.net.*;
import java.util.*;

public class CommandParser {

    private static Parser parser;

    public CommandParser() {
        try{
            prepareParser();
        } catch (Exception e) {
            System.out.println("Encountered error while preparing parser!");
        }
    }

    
    public Command parse(String input) {
        if (input.split(" ").length < 2) {
            return parseSimpleInput(input);
        } else {
            return parseComplexInput(input);
        }
    }

    private Command parseSimpleInput(String input) {
        Command cmd = new Command();
        CommandType cmdType = CommandType.getCommandTypeByStr(input); 
        if (cmdType == CommandType.INVALID) {
            System.out.println("Invalid command!");
            return null;
        } else {
            cmd.setCommandType(cmdType);
            return cmd;
        }
    }

    private Command parseComplexInput(String input) {
        Optional<ParseResult> optResult = parser.tryParse(input);
        if (optResult.isPresent()) {
            Command cmd = new Command();
            var result = optResult.get();
            String commandType = result.allCommands().get(0).name();
            // String template = res.allCommands().get(0).template();
            List<Argument> cmdArgs = result.allCommands().get(0).arguments();

            switch (commandType) {
                case "todo":
                    parseTodoArgs(cmd,cmdArgs);
                    break;
                case "deadline":
                    parseDeadlineArgs(cmd,cmdArgs);
                    break;
                case "event":
                    parseEventArgs(cmd,cmdArgs);
                    break;
                case "done":
                    parseDoneArgs(cmd,cmdArgs);
                    break;
                case "delete":
                    parseDeleteArgs(cmd,cmdArgs);
                    break;
                default:
                    System.out.println("Invalid command!");
                    cmd = null;
                    break;
            }
            return cmd;
        } else {
            System.out.println("Could not parse command!!");
            return null;
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
        for ( Argument a : cmdArgs) {
            String argName =  a.name();
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

    private void parseDeadlineArgs(Command cmd, List<Argument> cmdArgs) {
        cmd.setCommandType(CommandType.ADD);
        cmd.setTaskType(TaskType.DEADLINE);
        for ( Argument a : cmdArgs) {
            String argName =  a.name();
            String argVal = a.value().get();
            switch (argName) {
                case "task-hint":
                    cmd.setTaskDescription(argVal);
                    break;
                case "date-hint":
                    cmd.setTimeInfo(argVal);
                    break;
                default:
                    System.out.println("Unrecognised argument: " + argName);
                    break;
            }
        }
    }

    private void parseEventArgs(Command cmd, List<Argument> cmdArgs) {
        cmd.setCommandType(CommandType.ADD);
        cmd.setTaskType(TaskType.EVENT);
        for ( Argument a : cmdArgs) {
            String argName =  a.name();
            String argVal = a.value().get();
            switch (argName) {
                case "task-hint":
                    cmd.setTaskDescription(argVal);
                    break;
                case "date-hint":
                    cmd.setTimeInfo(argVal);
                    break;
                default:
                    System.out.println("Unrecognised argument: " + argName);
                    break;
            }
        }
    }
    private void parseDoneArgs(Command cmd, List<Argument> cmdArgs) {
        cmd.setCommandType(CommandType.DONE);
        for ( Argument a : cmdArgs) {
            String argName =  a.name();
            String argVal = a.value().get();
            switch (argName) {
                case "list-idx-hint":
                    cmd.setTaskDescription(argVal);
                default:
                    System.out.println("Unrecognised argument: " + argName);
                    break;
            }
        }
    }
    private void parseDeleteArgs(Command cmd, List<Argument> cmdArgs) {
        cmd.setCommandType(CommandType.DELETE);
        for ( Argument a : cmdArgs) {
            String argName =  a.name();
            String argVal = a.value().get();
            switch (argName) {
                case "list-idx-hint":
                    cmd.setTaskDescription(argVal);
                default:
                    System.out.println("Unrecognised argument: " + argName);
                    break;
            }
        }
    }




}
