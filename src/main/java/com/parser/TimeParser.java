package com.parser;

import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.List;
import java.util.Optional;

import com.dopsun.chatbot.cli.Argument;
import com.dopsun.chatbot.cli.ParseResult;
import com.dopsun.chatbot.cli.Parser;
import com.exceptions.InvalidArgumentException;
import com.exceptions.InvalidCommandException;
import com.time.Time;

public class TimeParser extends ParserBase {

    private Parser parser;

    /**
     * Constructor. Initialise internal parser
     */
    public TimeParser() throws URISyntaxException {
        super();
        // File.separator
        String commandSetPath = System.getProperty("user.dir") + "/data/input/time-data.properties";
        String trainingPath = System.getProperty("user.dir") + "/data/input/training-data.yml";
        parser = prepareParser(commandSetPath, trainingPath);
    }

    /**
     * API to parse a string
     * @param input command to be parsed
     * @return
     */
    public Time parse(String input) {
        LocalDateTime localTime = LocalDateTime.parse(input, DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
        return new Time(localTime);
    }

}
