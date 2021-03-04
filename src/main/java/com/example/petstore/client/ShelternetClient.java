package com.example.petstore.client;

import com.example.petstore.model.AnimalIds;
import com.example.petstore.model.AnimalResponseFromShelternet;

public interface ShelternetClient {
    AnimalResponseFromShelternet requestAnimals(AnimalIds animalIds);
}
