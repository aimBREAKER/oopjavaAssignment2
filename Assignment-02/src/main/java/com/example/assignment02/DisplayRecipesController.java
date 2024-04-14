package com.example.assignment02;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class DisplayRecipesController implements Initializable {

    @FXML
    private ListView<Recipe> displayRecipes;

    @FXML
    private Label errorLabel;

    @FXML
    private Button clickButton;

    @FXML
    private ImageView imageView;

    private List<Recipe> recipes;

    private SceneChanger sceneChanger;

    public void setSceneChanger(SceneChanger sceneChanger) {
        this.sceneChanger = sceneChanger;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Initializing the ListView with an empty list
        displayRecipes.setItems(FXCollections.observableArrayList());
        displayRecipes.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            clickButton.setDisable(newValue == null);
            if (newValue != null) {
                // Display the selected recipe's image
                imageView.setImage(new Image(newValue.getImageUrl()));
            }
        });
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
        displayRecipes.setItems(FXCollections.observableArrayList(recipes));
    }

    @FXML
    private void getDetailsOfRecipe(ActionEvent event) {
        Recipe selectedRecipe = displayRecipes.getSelectionModel().getSelectedItem();
        if (selectedRecipe != null) {
            try {
                // Pass the selected recipe to the next scene using the SceneChanger
                DetailedRecipe detailedRecipe = ApiUtility.getDetailedRecipeById(selectedRecipe.getId());
                SceneChanger.changeScene(detailedRecipe, event, "detailed-recipe.fxml", recipes );

            } catch (Exception e) {
                // Show error in the label
                System.out.println(e.getMessage());
                errorLabel.setText("Error occurred: " + e.getMessage());
            }
        } else {
            // Show an error or prompt to select a recipe
            errorLabel.setText("Please select a recipe.");
        }
    }
}
