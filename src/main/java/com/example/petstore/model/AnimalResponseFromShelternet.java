package com.example.petstore.model;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
public class AnimalResponseFromShelternet {
     List<AnimalResponseFromShelternetDTO> animals;

     public AnimalResponseFromShelternet(List<AnimalResponseFromShelternetDTO> animals) {
          this.animals = animals;
     }
}
