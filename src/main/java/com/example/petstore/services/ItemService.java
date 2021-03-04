package com.example.petstore.services;

import com.example.petstore.model.entities.ItemEntity;
import com.example.petstore.repository.ItemRepository;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public ItemEntity addItemInStock(ItemEntity item) {
        return itemRepository.save(item);
    }
}
