package com.example.assignment02;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.List;

public class SearchController {

    @FXML
    private TextField cuisineField, queryField;

    @FXML
    private Label errorLabel;

    @FXML
    void onClickSearch(ActionEvent event) {
        if (cuisineField.getText().isEmpty() || queryField.getText().isEmpty()) {
            errorLabel.setText("Enter the fields");
        } else {
            try {
                List<Recipe> allRecipes = ApiUtility.fetchAllRecipes("chicken", "american");
                SceneChanger.changeScenes(event, "display-recipes.fxml", allRecipes);
            } catch (IOException | InterruptedException e) {
                // Handle the exception gracefully by displaying an error message
                errorLabel.setText("Error occurred: " + e.getMessage());
            }
        }
    }
}
