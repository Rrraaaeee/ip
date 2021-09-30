package com.parser;

import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import com.dopsun.chatbot.cli.Parser;
import com.time.Time;
import com.ui.Ui;

public class TimeParser extends ParserBase {

    // private Parser parser;
    private Ui ui;
    private final String[] possibleFormats = {
        "dd/MM/yyyy/HHmm",
        "dd/M/yyyy/HHmm",
        "d/MM/yyyy/HHmm",
        "d/M/yyyy/HHmm",
        "dd/MM/HHmm",
        "dd/M/HHmm",
        "d/MM/HHmm",
        "d/M/HHmm",

        "HHmm/dd/MM/yyyy",
        "HHmm/dd/M/yyyy",
        "HHmm/d/MM/yyyy",
        "HHmm/d/M/yyyy",
        "HHmm/dd/MM",
        "HHmm/dd/M",
        "HHmm/d/MM",
        "HHmm/d/M"

    };

    /**
     * Constructor. Initialise internal parser
     */
    public TimeParser(Ui ui) throws URISyntaxException {
        super();
        this.ui = ui;
    }

    /**
     * API to parse a string
     * @param input command to be parsed
     * @return
     */
    public Time parse(String input) {
        String inputFormatted =  input.replace(' ', '/');
        for (String format : possibleFormats) {
            try {
                ui.showText("Trying to parse time: " + input + " -> " + format);
                LocalDateTime localTime = LocalDateTime.parse(inputFormatted, DateTimeFormatter.ofPattern(format));
                return new Time(localTime);
            } catch (DateTimeParseException e) {
                continue;
            }
        }

        ui.showText("Failed to parse time!");
        return new Time();
    }

}
