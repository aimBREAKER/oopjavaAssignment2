package com.example.assignment02;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("search-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Recipe Masters");
        stage.getIcons().add(new Image("data:image/png;base64,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"));
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}