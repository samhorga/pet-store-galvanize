package com.example.petstore.services;

import com.example.petstore.model.AnimalEntity;
import com.example.petstore.repository.AnimalRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnimalService {

    private final AnimalRepository animalRepository;

    public AnimalService(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    public List<AnimalEntity> getAnimals() {
        return this.animalRepository.findAll();
    }
}
