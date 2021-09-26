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


    public void showInvalidFilePathError() {
        show("Parser received invalid input file path!");
    }

    public void showInvalidCommandError() {
        show("Invalid Command!");
    }

    public void showInvalidCommandArgumentError() {
        show("Invalid Command Argument!");
    }

    public void showInvalidNumberError() {
        show("Invalid integer value!");
    }

    private void show(String printMessage) {
        printStream.println(printMessage);
    }



}
