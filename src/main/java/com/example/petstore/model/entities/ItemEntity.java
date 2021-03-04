package com.example.petstore.model.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
public class ItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long sku;
    private String category;
    private String animalType;
    private String brand;
    private String name;
    private String description;
    private Double price;
    private Integer quantity;

    public ItemEntity(String category, String animalType, String brand, String name, String description, Double price) {
        this.category = category;
        this.animalType = animalType;
        this.brand = brand;
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = 0;
    }
}
