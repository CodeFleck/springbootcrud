package com.hatchways.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Recipe {

    private String name;
    private List<String> ingredients;
    private List<String> instructions;
}
