package com.example.petstore.TestUtils;

import com.example.petstore.model.AnimalEntity;

import java.time.LocalDate;
import java.util.List;

public class Mocks {

    public static List<AnimalEntity> getAnimals() {
        AnimalEntity animal = new AnimalEntity("First", "DOG", LocalDate.of(2012, 2, 4), "M", "WHITE", "FRIENDLY");
        animal.setId(1L);
        AnimalEntity animal1 = new AnimalEntity("Second", "CAT", LocalDate.of(2012, 2, 4), "F", "GRAY", "NOT-FRIENDLY");
        animal.setId(2L);

        return List.of(animal, animal1);
    }
}
