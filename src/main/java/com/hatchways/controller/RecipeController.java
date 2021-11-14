package com.hatchways.controller;

import com.hatchways.exception.RecipeException;
import com.hatchways.facade.RecipeFacade;
import com.hatchways.model.Recipe;
import com.hatchways.model.RecipeDetailsResponse;
import com.hatchways.model.RecipeNamesResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
public class RecipeController {

    @Autowired
    RecipeFacade facade;

    @GetMapping("/recipes")
    public ResponseEntity<RecipeNamesResponse> getAllRecipeNames() {
        RecipeNamesResponse response = facade.getAllRecipeNames();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/recipes/details/{recipeName}")
    public ResponseEntity<RecipeDetailsResponse> getRecipeDetails(@PathVariable String recipeName) {
        RecipeDetailsResponse response = facade.getRecipeDetails(recipeName);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/recipes")
    public ResponseEntity<String> insertRecipe(@RequestBody Recipe recipe) {
        try {
            Recipe newRecipe = facade.insertRecipe(recipe);
            URI location = URI.create(String.format("/recipes/%s", newRecipe.getName()));
            return ResponseEntity.created(location).build();
        } catch (RecipeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/recipes")
    public ResponseEntity<String> updateRecipe(@RequestBody Recipe recipe) {
        try {
            Recipe updatedRecipe = facade.updateRecipe(recipe);
            URI location = URI.create(String.format("/recipes/%s", updatedRecipe.getName()));
            return ResponseEntity.created(location).build();
        } catch (RecipeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

}
