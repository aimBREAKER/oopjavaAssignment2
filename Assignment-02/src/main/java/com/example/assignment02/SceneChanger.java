package com.example.assignment02;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class SceneChanger {

    public static void changeScenes(ActionEvent event, String fxmlFileName, List<Recipe> recipes) throws IOException {
        FXMLLoader loader = new FXMLLoader(SceneChanger.class.getResource(fxmlFileName));
        Parent root = loader.load();

        // Access the controller of the new scene
        DisplayRecipesController displayRecipesController = loader.getController();

        // Set the list of recipes in the controller
        displayRecipesController.setRecipes(recipes);

        Scene scene = new Scene(root);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public static void changeScene(DetailedRecipe detailedRecipe,ActionEvent event, String fxmlFileName, List<Recipe> recipes) {
        try {
            FXMLLoader loader = new FXMLLoader(SceneChanger.class.getResource(fxmlFileName));
            Parent root = loader.load();

            // Access the controller of the new FXML file
            // Make sure the controller class has a method to receive the objectToPass
            DetailedRecipeController controller = loader.getController();
            controller.setDetailedRecipe(detailedRecipe);
            controller.setRecipes(recipes);

            // Set up the new scene
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            // Handle exceptions or display error message
        }
    }
}