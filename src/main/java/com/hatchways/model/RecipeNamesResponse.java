package com.hatchways.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class RecipeNamesResponse {

    private List<String> recipeNames;
}
