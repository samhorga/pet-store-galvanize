package com.example.petstore.services;

import com.example.petstore.client.ShelternetClient;
import com.example.petstore.model.entities.AnimalEntity;
import com.example.petstore.model.AnimalIds;
import com.example.petstore.model.AnimalResponseFromShelternet;
import com.example.petstore.model.AnimalResponseFromShelternetDTO;
import com.example.petstore.repository.AnimalRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnimalService {

    private final AnimalRepository animalRepository;
    private final ShelternetClient shelternetClient;

    public AnimalService(AnimalRepository animalRepository, ShelternetClient shelternetClient) {
        this.animalRepository = animalRepository;
        this.shelternetClient = shelternetClient;
    }

    public List<AnimalEntity> getAnimals() {
        return this.animalRepository.findAll();
    }

    public AnimalResponseFromShelternet requestAnimalsFromShelternet(AnimalIds animalIds) {
        AnimalResponseFromShelternet animals = this.shelternetClient.requestAnimals(animalIds);
        animals.getAnimals().forEach(animal -> animalRepository.save(MapFromResponseFromShelternetDTOtoEntity(animal)));
        return animals;
    }

    private AnimalEntity MapFromResponseFromShelternetDTOtoEntity(AnimalResponseFromShelternetDTO animal) {
        return new AnimalEntity(animal.getName(), animal.getSpecies(), animal.getBirthDate(), animal.getSex(), animal.getColor(), "");
    }
}
