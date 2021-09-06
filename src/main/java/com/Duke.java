// import command.Command;
import com.command.CommandHandler;
import com.command.CommandType;
import com.dopsun.chatbot.cli.*;
import com.dopsun.chatbot.cli.input.*;

import java.nio.file.*;
import java.net.*;
import java.util.*;


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

    public static Parser parser;

    /** Main */
    public static void main(String[] args) {
        System.out.println("Hello from\n" + LOGO + GREETING);
        try{
            prepareParser();
        } catch (Exception e) {
            System.out.println("Encountered error while preparing parser!");
        }



        /*
        CommandHandler commandHandler = new CommandHandler();
        CommandParser commandParser = CommandParser.getCommandParser();

        while (true) {
            Command cmd = commandParser.parseNextCommand();
            commandHandler.handlerCommand(cmd);
            if (cmd.isType(CommandType.BYE)) {
                return;
            }
        }
        */

        Optional<ParseResult> optResult = parser.tryParse("todo my deadline by Friday");
        if (optResult.isPresent()) {
            var res = optResult.get();
            String name = res.allCommands().get(0).name();
            String template = res.allCommands().get(0).template();
            List<Argument> cmdArgs = res.allCommands().get(0).arguments();
            for ( Argument a : cmdArgs) {
                System.out.println(a.name());
                System.out.println(a.value().get());
            }

            System.out.println(name);
            System.out.println(template);
        }




    }


    public static void prepareParser() throws URISyntaxException {
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
}


