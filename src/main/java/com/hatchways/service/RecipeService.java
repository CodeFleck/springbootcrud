package com.hatchways.service;

import com.hatchways.exception.RecipeException;
import com.hatchways.helper.RecipeHelper;
import com.hatchways.model.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
@Slf4j
public class RecipeService {

    public RecipeNamesResponse getAllRecipeNames() {
        RecipeList recipes = RecipeHelper.getRecipesData();
        return RecipeNamesResponse.builder()
                .recipeNames(recipes.getRecipes().stream().map(Recipe::getName).collect(Collectors.toList()))
                .build();
    }

    public RecipeDetailsResponse getRecipeDetails(String recipeName) {
        RecipeList recipes = RecipeHelper.getRecipesData();
        Optional<Recipe> recipe = findRecipeByName(recipes, recipeName);
        return recipe.map(value -> RecipeDetailsResponse.builder()
                .details(buildRecipeDetails(value))
                .build()).orElse(null);
    }

    private RecipeDetails buildRecipeDetails(Recipe recipe) {
        return RecipeDetails.builder()
                .ingredients(recipe.getIngredients())
                .numSteps(recipe.getInstructions().size())
                .build();
    }

    private Optional<Recipe> findRecipeByName(RecipeList recipes, String recipeName) {
        return recipes.getRecipes().stream()
                .filter(recipe -> recipe.getName().equals(recipeName)).findAny();
    }

    public Recipe insertRecipe(Recipe recipe) throws RecipeException {
        RecipeList recipes = RecipeHelper.getRecipesData();
        Optional<Recipe> recipeOptional = findRecipeByName(recipes, recipe.getName());
        if (recipeOptional.isPresent()) {
            throw new RecipeException("Recipe already exists");
        }
        recipes.getRecipes().add(recipe);
        RecipeHelper.insertRecipeData(recipes);
        return recipe;
    }

    public Recipe updateRecipe(Recipe recipe) throws RecipeException{
        RecipeList recipes = RecipeHelper.getRecipesData();
        Optional<Recipe> recipeOptional = findRecipeByName(recipes, recipe.getName());
        if (!recipeOptional.isPresent()) {
            throw new RecipeException("Recipe does not exist");
        }
        recipes.getRecipes().remove(recipeOptional.get());
        recipes.getRecipes().add(recipe);
        RecipeHelper.insertRecipeData(recipes);
        return recipe;
    }
}
