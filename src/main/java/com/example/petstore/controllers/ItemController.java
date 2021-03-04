package com.example.petstore.controllers;

import com.example.petstore.model.entities.ItemEntity;
import com.example.petstore.services.ItemService;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/items")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService animalService) {
        this.itemService = animalService;
    }

    @PostMapping
    public ItemEntity requestAnimalsFromShelternet(@RequestBody ItemEntity item) {
        return itemService.addItemInStock(item);
    }
}
