package com.parser;

import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.Year;

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
        "HHmm/dd/MM/yyyy",
        "HHmm/dd/M/yyyy",
        "HHmm/d/MM/yyyy",
        "HHmm/d/M/yyyy"
    };

    /**
     * If possible formats return false, it must be one of these 
     * alternative formats
     */
    private final String[] possibleFormatsBackup = {
        "dd/MM/HHmm/yyyy",
        "dd/M/HHmm/yyyy",
        "d/MM/HHmm/yyyy",
        "d/M/HHmm/yyyy",
        "HHmm/dd/MM/yyyy",
        "HHmm/dd/M/yyyy",
        "HHmm/d/MM/yyyy",
        "HHmm/d/M/yyyy"
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
        ui.showText(input);
        String inputFormatted = input.replace(' ', '/');
        Time time = tryParse(inputFormatted, possibleFormats);
        if (time != null) {
            return time;
        }

        // use backup formats
        Integer currentYear =  Year.now().getValue();
        inputFormatted += "/" + currentYear.toString();
        time = tryParse(inputFormatted, possibleFormatsBackup);
        if (time != null) {
            return time;
        }

        ui.showText("Failed to parse time!");
        return new Time();
    }

    private Time tryParse(String input, String[] formats) {
        for (String format : formats) {
            try {
                LocalDateTime localTime = LocalDateTime.parse(input, DateTimeFormatter.ofPattern(format));
                return new Time(localTime);
            } catch (DateTimeParseException e) {
                continue;
            }
        }
        return null;
    }
}
