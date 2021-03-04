package com.example.petstore.client;

import com.example.petstore.model.AnimalIds;
import com.example.petstore.model.AnimalResponseFromShelternet;
import com.example.petstore.model.AnimalResponseFromShelternetDTO;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@Profile("test")
public class TestShelternetClient implements ShelternetClient {
    @Override
    public AnimalResponseFromShelternet requestAnimals(AnimalIds animalIds) {
        return animalIds.getAnimalIds().stream()
                .map(id -> new AnimalResponseFromShelternet(List.of(new AnimalResponseFromShelternetDTO(id, "TEST", "DOG",
                        LocalDate.of(2012, 4, 2), "M", "RED")))).findFirst().orElseThrow();
    }
}
