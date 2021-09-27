package com.ui;

import java.io.PrintStream;

import com.Duke;

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

public class Ui {
    private final String FONT = "Arial";
    private final Integer FONT_SIZE = 13;

    private PrintStream printStream;
    private Duke duke;

    // javafx
    private Stage stage;
    private Button sendButton;
    private Image userImg = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image dukeImg = new Image(this.getClass().getResourceAsStream("/images/normal.png"));
    private Scene scene;
    private ScrollPane scrollPane;
    private TextField userInput;
    private VBox dialogContainer;

    public Ui (PrintStream printStream) {
        this.printStream = printStream; // for debugging
    }

    // https://se-education.org/guides/tutorials/javaFx.html
    /**
     * Create stage, scene, nodes, etc
     **/
    public void create(Stage stage, Duke duke) {

        this.stage = stage;
        this.duke = duke;

        // Step 1. Setting up required components
        // The container for the content of the chat to scroll.
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        userInput.setFont(new Font(FONT, FONT_SIZE));

        sendButton = new Button("Send");
        sendButton.setFont(new Font(FONT, FONT_SIZE));

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        stage.setScene(scene);

        //Step 2. Formatting the window to look as expected
        stage.setTitle("Duke Chatbot");
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

        userInput.setPrefWidth(330.0);
        userInput.setPrefHeight(60.0);

        sendButton.setPrefWidth(55.0);
        sendButton.setPrefHeight(60.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));


        //Part 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });
    }

    public void showUi() {
        stage.show();
    }

    /**
     * Prints greet message
     **/
    public void greet() {
        Label greet = new Label("Hello!!!");
        greet.setFont(new Font(FONT, FONT_SIZE));
        dialogContainer.getChildren().addAll(
            DialogBox.getDukeDialog(greet, new ImageView(dukeImg))
        );
    }

    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(duke.getResponse(userInput.getText()));
        userText.setFont(new Font(FONT, FONT_SIZE));
        dukeText.setFont(new Font(FONT, FONT_SIZE));
        dialogContainer.getChildren().addAll(
            DialogBox.getUserDialog(userText, new ImageView(userImg)),
            DialogBox.getDukeDialog(dukeText, new ImageView(dukeImg))
        );
        userInput.clear();
    }




    public void showText(String text) {
        printStream.println(text);
    }

    public void showInvalidFilePathError() {
        showText("Parser received invalid input file path!");
    }

    public void showInvalidCommandError() {
        showText("Invalid Command!");
    }

    public void showInvalidCommandArgumentError() {
        showText("Invalid Command Argument!");
    }

    public void showInvalidNumberError() {
        showText("Invalid integer value!");
    }




}
