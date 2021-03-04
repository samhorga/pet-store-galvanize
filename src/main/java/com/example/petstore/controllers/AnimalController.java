package com.example.petstore.controllers;

import com.example.petstore.model.entities.AnimalEntity;
import com.example.petstore.model.AnimalIds;
import com.example.petstore.model.AnimalResponseFromShelternet;
import com.example.petstore.services.AnimalService;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/request")
    public AnimalResponseFromShelternet requestAnimalsFromShelternet(@RequestBody AnimalIds animalIds) {
        return animalService.requestAnimalsFromShelternet(animalIds);
    }
}
