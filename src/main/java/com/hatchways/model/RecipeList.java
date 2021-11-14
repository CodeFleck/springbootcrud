package com.hatchways.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class RecipeList {

    private List<Recipe> recipes;
}
