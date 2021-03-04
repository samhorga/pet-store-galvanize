package com.example.petstore.model.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class AnimalEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String species;
    private LocalDate birthDate;
    private String sex;
    private String color;
    private String notes;

    public AnimalEntity(String name, String species, LocalDate birthDate, String sex, String color, String notes) {
        this.name = name;
        this.species = species;
        this.birthDate = birthDate;
        this.sex = sex;
        this.color = color;
        this.notes = notes;
    }
}
