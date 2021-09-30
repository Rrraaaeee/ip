package com.ui;

import java.io.PrintStream;

public class Ui {

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

    static final String SEPERATOR = "---------------";

    private PrintStream printStream;

    public Ui (PrintStream printStream) {
        this.printStream = printStream;
    }

    public void showLogo() {
        show(LOGO);
    }

    public void showGreetingMessage() {
        show(GREETING);
    }

    public void showSeperator() {
        show(SEPERATOR);
    }

    public void showText(String text) {
        show(text);
    }

    /**
     * Exception message to show file path error
     **/
    public void showInvalidFilePathError() {
        show("Parser received invalid input file path!");
        showSeperator();
    }

    /**
     * Exception message to show invalid command error
     **/
    public void showInvalidCommandError() {
        show("I don't quite understand what do you mean, try type in [help] to see available commands.");
        showSeperator();
    }

    /**
     * Exception message to show invalid command argument error
     **/
    public void showInvalidCommandArgumentError() {
        show("You have keyed in invalid command argument.");
        showSeperator();
    }

    /**
     * Exception message to show invalid number (such as task id) error
     **/
    public void showInvalidNumberError() {
        show("Unrecognised number.");
        showSeperator();
    }

    public void showBye() {
        show("Bye bye :))");
    }

    private void show(String printMessage) {
        printStream.println(printMessage);
    }



}
