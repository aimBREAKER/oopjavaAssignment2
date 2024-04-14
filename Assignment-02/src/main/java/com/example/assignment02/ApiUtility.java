package com.example.assignment02;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ApiUtility {
    private static final String API_KEY = "7a743b1a08bf49629eeb9b9c22f828b5";
    private static final String API_HOST = "https://api.spoonacular.com/recipes/complexSearch";



    public static List<Recipe> fetchAllRecipes(String query, String cuisine) throws IOException, InterruptedException {
        String uri = API_HOST + "?apiKey=" + API_KEY;

        // Add query and cuisine parameters if provided
        if (query != null && !query.isEmpty()) {
            uri += "&query=" + query;
        }

        if (cuisine != null && !cuisine.isEmpty()) {
            uri += "&cuisine=" + cuisine;
        }

        HttpRequest httpRequest = buildRequest(uri);

        HttpClient client = HttpClient.newHttpClient();
        List<Recipe> recipes = new ArrayList<>();

        try {
            HttpResponse<String> httpResponse = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());

            int statusCode = httpResponse.statusCode();
            if (statusCode == 200) {
                String responseBody = httpResponse.body();
                System.out.println("Data fetched successfully: " + responseBody);

                // Parse and handle the response body containing all recipes
                recipes = parseRecipes(responseBody);
            } else {
                System.out.println("Failed to fetch data. Status code: " + statusCode);
                System.out.println("Response body: " + httpResponse.body());
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return recipes;
    }

    private static List<Recipe> parseRecipes(String responseBody) {
        List<Recipe> recipes = new ArrayList<>();

        // Parse JSON here and create Recipe objects
        // Example parsing using Gson library
        JsonObject responseObject = JsonParser.parseString(responseBody).getAsJsonObject();
        JsonArray resultsArray = responseObject.getAsJsonArray("results");

        if (resultsArray != null) {
            for (int i = 0; i < resultsArray.size(); i++) {
                JsonObject recipeObj = resultsArray.get(i).getAsJsonObject();
                String recipeName = recipeObj.get("title").getAsString();
                String imageUrl = recipeObj.get("image").getAsString();
                int id = recipeObj.get("id").getAsInt();

                Recipe recipe = new Recipe(recipeName, imageUrl,id);
                recipes.add(recipe);
            }
        }
        return recipes;
    }

    public static DetailedRecipe getDetailedRecipeById(int recipeId) throws IOException, InterruptedException {
        String uri = "https://api.spoonacular.com/recipes/" + recipeId + "/information?apiKey=" + API_KEY + "&includeNutrition=false";

        HttpRequest httpRequest = buildRequest(uri);

        HttpClient client = HttpClient.newHttpClient();
        DetailedRecipe detailedRecipe = null;

        try {
            HttpResponse<String> httpResponse = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());

            int statusCode = httpResponse.statusCode();
            if (statusCode == 200) {
                String responseBody = httpResponse.body();
                System.out.println("Data fetched successfully: " + responseBody);

                detailedRecipe = parseDetailedRecipe(responseBody);
            } else {
                System.out.println("Failed to fetch data. Status code: " + statusCode);
                System.out.println("Response body: " + httpResponse.body());
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return detailedRecipe;
    }

    private static DetailedRecipe parseDetailedRecipe(String responseBody) {
        DetailedRecipe detailedRecipe = null;

        JsonObject responseObject = JsonParser.parseString(responseBody).getAsJsonObject();
        String title = responseObject.getAsJsonPrimitive("title").getAsString();
        String image = responseObject.getAsJsonPrimitive("image").getAsString();

        double healthScore = responseObject.getAsJsonPrimitive("healthScore").getAsDouble();
        String readyInMinutes = responseObject.getAsJsonPrimitive("readyInMinutes").getAsString();
        System.out.println(readyInMinutes);
        // Adjust this part based on the actual structure of the API response
        // and the fields you want to extract for the DetailedRecipe object
        detailedRecipe = new DetailedRecipe(title, readyInMinutes, image, healthScore);

        return detailedRecipe;
    }


    private static HttpRequest buildRequest(String uri) {
        return HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .header("x-api-key", API_KEY)
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
    }
}
