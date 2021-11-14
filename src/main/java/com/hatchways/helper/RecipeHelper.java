package com.hatchways.helper;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.hatchways.model.RecipeList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.Objects;

@Slf4j
public class RecipeHelper {

    private RecipeHelper() {
        try {
            throw new IllegalAccessException("Utility class");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static RecipeList getRecipesData() {
        Gson gson = new Gson();
        JsonReader reader = null;
        try {
            File file = ResourceUtils.getFile(System.getProperty("user.dir") + "/data.json");
            reader = new JsonReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            log.error("Couldn't find data file");
            e.printStackTrace();
        }
        return gson.fromJson(Objects.requireNonNull(reader), RecipeList.class);
    }

    public static void insertRecipeData(RecipeList recipes) {
        try {
            Gson gson = new Gson();
            String recipesJson = gson.toJson(recipes);
            BufferedWriter writer = new BufferedWriter(new FileWriter("data.json"));
            writer.write(recipesJson);
            writer.close();
        } catch (IOException e) {
            log.error("Couldn't insert new recipe.");
            e.printStackTrace();
        }
    }
}
