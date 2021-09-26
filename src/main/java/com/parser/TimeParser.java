package com.parser;

import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.List;
import java.util.Optional;
import java.time.format.DateTimeParseException;

import com.dopsun.chatbot.cli.Argument;
import com.dopsun.chatbot.cli.ParseResult;
import com.dopsun.chatbot.cli.Parser;
import com.exceptions.InvalidArgumentException;
import com.exceptions.InvalidCommandException;
import com.time.Time;

public class TimeParser extends ParserBase {

    private Parser parser;
    private final String[] possibleFormats = {
        "dd/MM/yyyy HHmm",
        "dd/M/yyyy HHmm",
        "d/MM/yyyy HHmm",
        "d/M/yyyy HHmm",
        "dd/MM HHmm",
        "dd/M HHmm",
        "d/MM HHmm",
        "d/M HHmm",

        "HHmm dd/MM/yyyy",
        "HHmm dd/M/yyyy",
        "HHmm d/MM/yyyy",
        "HHmm d/M/yyyy",
        "HHmm dd/MM",
        "HHmm dd/M",
        "HHmm d/MM",
        "HHmm d/M",

        "dd/MM/yyyy Hmm",
        "dd/M/yyyy Hmm",
        "d/MM/yyyy Hmm",
        "d/M/yyyy Hmm",
        "dd/MM Hmm",
        "dd/M Hmm",
        "d/MM Hmm",
        "d/M Hmm",

        "Hmm dd/MM/yyyy",
        "Hmm dd/M/yyyy",
        "Hmm d/MM/yyyy",
        "Hmm d/M/yyyy",
        "Hmm dd/MM",
        "Hmm dd/M",
        "Hmm d/MM",
        "Hmm d/M"
    };

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
        for (String format : possibleFormats) {
            try {
                LocalDateTime localTime = LocalDateTime.parse(input, DateTimeFormatter.ofPattern(format));
                return new Time(localTime);
            } catch (DateTimeParseException e) {
                continue;
            }
        }

        System.out.println("Failed to parse time!");
        return new Time();
    }

}
