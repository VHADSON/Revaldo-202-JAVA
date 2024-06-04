package org.example.tugasmodulke6;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.example.tugasmodulke6.com.main.LibrarySystem;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) {
        Button button = new Button("START");
        button.setOnAction(event -> LibrarySystem.startLibrarySystem(stage));
        button.setPrefWidth(300);
        button.setPrefHeight(75);
        button.setFont(new Font("Tahoma", 14));

        button.setStyle("-fx-background-color: transparent; -fx-text-fill: transparent;");
        button.setOpacity(0.0);
        button.setTextFill(Color.TRANSPARENT);
        button.setTextFill(Color.TRANSPARENT);

        Image backgroundImage = new Image("file:src/main/resources/UI JavaFX2.png");
        BackgroundImage background = new BackgroundImage(backgroundImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT);

        VBox root = new VBox(10, button);
        root.setAlignment(Pos.CENTER);
        root.setBackground(new Background(background));
        Scene scene = new Scene(root, 600, 600);

        stage.getIcons().add(new Image("file:src/main/resources/Icon3.png"));
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
