package com.example.petstore.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Getter
public class AnimalIds {
    private List<Long> animalIds;

    public AnimalIds(List<Long> animalIds) {
        this.animalIds = animalIds;
    }

}
