package com.example.assignment02;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class DetailedRecipeController implements Initializable {

    @FXML
    private Label healthScoreLabel;

    @FXML
    private ImageView imageView;

    @FXML
    private Label priceLabel;

    @FXML
    private Label titleLabel;
    private List<Recipe> recipes;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void setDetailedRecipe(DetailedRecipe detailedRecipe) {
        titleLabel.setText(detailedRecipe.getTitle());
        healthScoreLabel.setText("Health Score: " + detailedRecipe.getHealthScore());
        priceLabel.setText("Ready In: " + detailedRecipe.getReadyInMinutes());

        // Load and display the image
        String imageUrl = detailedRecipe.getImage();
        Image image = new Image(imageUrl);
        imageView.setImage(image);
    }

    @FXML
    void goBack(ActionEvent event) throws IOException {
        SceneChanger.changeScenes(event, "display-recipes.fxml",recipes);
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }
}
