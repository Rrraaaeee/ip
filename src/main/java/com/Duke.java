package com;

import java.net.URISyntaxException;
import java.util.Scanner;

import com.command.Command;
import com.command.CommandHandler;
import com.exceptions.FinishAppException;
import com.exceptions.InvalidArgumentException;
import com.exceptions.InvalidCommandException;
import com.parser.CommandParser;
import com.storage.Storage;
import com.ui.Ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;


public class Duke extends Application {
    private Scanner scanner;
    private Ui ui;
    private CommandHandler commandHandler;
    private CommandParser commandParser;
    private Storage storage;

    // javafx
    private Button sendButton;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));
    private Scene scene;
    private ScrollPane scrollPane;
    private TextField userInput;
    private VBox dialogContainer;


    /**
     * Default constructor needed to instantiate javafx
     **/
    public Duke() {
    }

    /**
     * Constructor that initialises all internal handler objects
     * such as scanner, ui, parser and commandHandler
     **/
    public Duke(String storagePath) {
        ui = new Ui(System.out);
        scanner = new Scanner(System.in); // Create a Scanner object
        storage = new Storage(storagePath);
        commandHandler = new CommandHandler(storage, ui);

        try {
            commandParser = new CommandParser();
        } catch (URISyntaxException e) {
            ui.showInvalidFilePathError();
        }
    }

    /**
     * Duke entry
     */
    public void run() {
        startUp();
        ui.showText("[TODO] Dont forget to clean up generic exception error");
        ui.showText("[TODO] Use File.seperator so that path works on both mac and window");
        ui.showText("[TODO] How to integrate files into single jar??");
        // event loop
        while (true) {
            nextEvent();
        }
    }

    private void startUp() {
        ui.showLogo();
        ui.showGreetingMessage();
        ui.showSeperator();
        storage.loadTasks();

    }
    private void nextEvent() {
        try {
            String input = scanner.nextLine();
            Command command = commandParser.parse(input);
            if (command != null) {
                commandHandler.handlerCommand(command);
            }
        } catch (InvalidCommandException e) {
            ui.showInvalidCommandError();
        } catch (InvalidArgumentException e) {
            ui.showInvalidCommandArgumentError();
        } catch (NumberFormatException e) {
            ui.showInvalidNumberError();
        } catch (FinishAppException e) {
            scanner.close();
            System.exit(0);
        }
    }


    /**
     * Main entry point
     * */
    public static void main(String[] args) {
        String storagePath = System.getProperty("user.dir") + "/tmp/storage.txt";
        new Duke(storagePath).run();
    }




    // https://se-education.org/guides/tutorials/javaFx.html
    @Override
    public void start(Stage stage) {
        // Step 1. Setting up required components

        // The container for the content of the chat to scroll.
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);


        userInput = new TextField();
        sendButton = new Button("Send");
        sendButton.setFont(new Font("Arial", 11));
        userInput.setFont(new Font("Arial", 11));

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();

        //Step 2. Formatting the window to look as expected
        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        mainLayout.setPrefSize(400.0, 600.0);

        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        // You will need to import `javafx.scene.layout.Region` for this.
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        // Step 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        userInput.setOnAction((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));


        //Part 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });


        // more code to be added here later
    }


    /**
     * Iteration 1:
     * Creates a label with the specified text and adds it to the dialog container.
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */
    private Label getDialogLabel(String text) {
        // You will need to import `javafx.scene.control.Label`.
        Label textToAdd = new Label(text);
        textToAdd.setFont(new Font("Arial", 11));
        textToAdd.setWrapText(true);
        return textToAdd;
    }

    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));
        userText.setFont(new Font("Arial", 11));
        dukeText.setFont(new Font("Arial", 11));
        dialogContainer.getChildren().addAll(
            DialogBox.getUserDialog(userText, new ImageView(user)),
            DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
        userInput.clear();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    private String getResponse(String input) {
        return "Duke heard: " + input;
    }



}


