package com.hatchways.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class RecipeDetails {

    private List<String> ingredients;
    private int numSteps;
}
