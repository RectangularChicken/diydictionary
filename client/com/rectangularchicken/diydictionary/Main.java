package com.rectangularchicken.diydictionary;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        Parent title = title();

        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(title, 300, 275));
        primaryStage.show();
    }

    private Parent title() {
        return new Group();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
