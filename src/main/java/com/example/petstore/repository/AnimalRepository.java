package com.example.petstore.repository;

import com.example.petstore.model.entities.AnimalEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimalRepository extends JpaRepository<AnimalEntity, Long> {
}
