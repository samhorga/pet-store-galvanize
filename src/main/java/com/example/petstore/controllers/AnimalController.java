package com.example.petstore.controllers;

import com.example.petstore.model.AnimalEntity;
import com.example.petstore.services.AnimalService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/animals")
public class AnimalController {

    private final AnimalService animalService;

    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    @GetMapping
    public List<AnimalEntity> getAnimals() {
        return animalService.getAnimals();
    }
}
