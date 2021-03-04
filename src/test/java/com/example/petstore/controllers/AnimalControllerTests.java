package com.example.petstore.controllers;

import com.example.petstore.model.entities.AnimalEntity;
import com.example.petstore.model.AnimalIds;
import com.example.petstore.repository.AnimalRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class AnimalControllerTests {

    @Autowired
    private AnimalRepository animalRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void WhenThereAreAnimalsInThePetStore_ThenshouldReturnAnimals() throws Exception {
        AnimalEntity animal = new AnimalEntity("First", "DOG", LocalDate.of(2012, 2, 4), "M", "WHITE", "FRIENDLY");
        AnimalEntity animal1 = new AnimalEntity("Second", "CAT", LocalDate.of(2012, 2, 4), "F", "GRAY", "NOT-FRIENDLY");

        animalRepository.save(animal);
        animalRepository.save(animal1);

        mockMvc.perform(get("/animals"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*", hasSize(2)))
                .andExpect(jsonPath("[0].name").value("First"))
                .andExpect(jsonPath("[0].species").value("DOG"))
                .andExpect(jsonPath("[0].sex").value("M"))
                .andExpect(jsonPath("[0].color").value("WHITE"))
                .andExpect(jsonPath("[0].notes").value("FRIENDLY"))
                .andExpect(jsonPath("[1].name").value("Second"))
                .andExpect(jsonPath("[1].species").value("CAT"))
                .andExpect(jsonPath("[1].sex").value("F"))
                .andExpect(jsonPath("[1].color").value("GRAY"))
                .andExpect(jsonPath("[1].notes").value("NOT-FRIENDLY"));
    }

    @Test
    public void whenRequestAnimalsFromShelternet_AnimalsShouldTransferINThePetStore() throws Exception {
        AnimalIds animalIds = new AnimalIds(List.of(1L, 2L));

        mockMvc.perform(post("/animals/request")
                .content(objectMapper.writeValueAsString(animalIds))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.animals.[0].name").value("TEST"))
                .andExpect(jsonPath("$.animals.[0].species").value("DOG"))
                .andExpect(jsonPath("$.animals.[0].birthDate").value("2012-04-02"))
                .andExpect(jsonPath("$.animals.[0].sex").value("M"))
                .andExpect(jsonPath("$.animals.[0].color").value("RED"));
    }
}
