package com.example.petstore.model;

import lombok.Value;

import java.time.LocalDate;

@Value
public class AnimalResponseFromShelternetDTO {
    Long id;
    String name;
    String species;
    LocalDate birthDate;
    String sex;
    String color;
}
