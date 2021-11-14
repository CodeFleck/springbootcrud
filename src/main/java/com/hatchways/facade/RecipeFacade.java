package com.hatchways.facade;

import com.hatchways.exception.RecipeException;
import com.hatchways.model.Recipe;
import com.hatchways.model.RecipeDetailsResponse;
import com.hatchways.model.RecipeNamesResponse;
import com.hatchways.service.RecipeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class RecipeFacade {

    RecipeService service;

    public RecipeNamesResponse getAllRecipeNames() {
        return service.getAllRecipeNames();
    }

    public RecipeDetailsResponse getRecipeDetails(String recipeName) {
        return service.getRecipeDetails(recipeName);
    }

    public Recipe insertRecipe(Recipe recipe) throws RecipeException {
        return service.insertRecipe(recipe);
    }

    public Recipe updateRecipe(Recipe recipe) throws RecipeException {
        return service.updateRecipe(recipe);
    }
}
