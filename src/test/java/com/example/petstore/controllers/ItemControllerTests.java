package com.example.petstore.controllers;

import com.example.petstore.model.entities.ItemEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ItemControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void whenPetStoreAddItems_thenTheStoreCatalogShouldShowWithZeroStock() throws Exception {
        ItemEntity item = new ItemEntity("FOOD", "DOG", "LAYS", "CHIPS", "THIS IS CHIPS FOR DOG", 14.59);

        mockMvc.perform(post("/items")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(item)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.sku").exists())
                .andExpect(jsonPath("$.category").value("FOOD"))
                .andExpect(jsonPath("$.animalType").value("DOG"))
                .andExpect(jsonPath("$.brand").value("LAYS"))
                .andExpect(jsonPath("$.name").value("CHIPS"))
                .andExpect(jsonPath("$.description").value("THIS IS CHIPS FOR DOG"))
                .andExpect(jsonPath("$.price").value(14.59))
                .andExpect(jsonPath("$.quantity").value(0));

    }
}
