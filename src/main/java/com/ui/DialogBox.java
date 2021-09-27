package com.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;


public class DialogBox extends HBox {

    private Label text;
    private Circle displayPicture;

    /**
     * Constructor to set dialogbox parameters
     */
    // https://github.com/tlylt/ip/blob/master/src/main/java/tlylt/haha/DialogBox.java
    public DialogBox(Label l, ImageView iv) {
        text = l;
        text.setWrapText(true);
        text.setBackground(new Background(new BackgroundFill(Color.YELLOWGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
        text.setPadding(new Insets(5, 5, 5, 5));
        text.setFont(new Font("Arial", 13));
        text.setEffect(new DropShadow(BlurType.GAUSSIAN, Color.GREY, 2, 10, 3, 3));

        displayPicture = new Circle(30.0, new ImagePattern(iv.getImage()));
        displayPicture.relocate(10, 10);
        displayPicture.setEffect(new DropShadow(BlurType.GAUSSIAN, Color.GREY, 2, 10, 1.5, 1.5));

        this.setAlignment(Pos.CENTER_RIGHT);
        this.setSpacing(10);
        // this.getChildren().addAll(circle, text, displayPicture);
        this.getChildren().addAll(text, displayPicture);
    }

    /**
    * Flips the dialog box such that the ImageView is on the left and text on the right.
    */
    private void flip() {
        this.setAlignment(Pos.CENTER_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    public static DialogBox getUserDialog(Label l, ImageView iv) {
        return new DialogBox(l, iv);
    }

    public static DialogBox getDukeDialog(Label l, ImageView iv) {
        var db = new DialogBox(l, iv);
        db.flip();
        return db;
    }

}
