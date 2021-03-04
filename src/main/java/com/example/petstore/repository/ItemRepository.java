package com.example.petstore.repository;

import com.example.petstore.model.entities.AnimalEntity;
import com.example.petstore.model.entities.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<ItemEntity, Long> {
}
