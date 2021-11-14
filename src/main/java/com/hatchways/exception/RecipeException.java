package com.hatchways.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.BAD_REQUEST, reason = "Recipe not found")
public class RecipeException extends Exception {

    public RecipeException(String errorMessage) {
        super(errorMessage);
    }
}
