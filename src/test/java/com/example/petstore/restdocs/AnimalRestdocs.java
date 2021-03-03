package com.example.petstore.restdocs;

import com.example.petstore.controllers.AnimalController;
import com.example.petstore.model.AnimalEntity;
import com.example.petstore.services.AnimalService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = AnimalController.class)
@AutoConfigureMockMvc
@AutoConfigureRestDocs
@ActiveProfiles("test")
public class AnimalRestdocs {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AnimalService animalService;

    @Test
    public void getAllAnimals() throws Exception {
        AnimalEntity animal = new AnimalEntity("First", "DOG", LocalDate.of(2012, 2, 4), "M", "WHITE", "FRIENDLY");
        AnimalEntity animal1 = new AnimalEntity("Second", "CAT", LocalDate.of(2012, 2, 4), "F", "GRAY", "NOT-FRIENDLY");
        animal.setId(1L);
        animal1.setId(2L);
        List<AnimalEntity> animals1 = List.of(animal, animal1);
        when(animalService.getAnimals()).thenReturn(animals1);

        mockMvc.perform(get("/animals"))
                .andExpect(status().isOk())
                .andDo(document("get-all-animals", responseFields(
                        fieldWithPath("[*].id").description("The ID of the animal."),
                        fieldWithPath("[*].name").description("The Name of the animal."),
                        fieldWithPath("[*].species").description("The Species of the animal."),
                        fieldWithPath("[*].birthDate").description("The BirthDate of the animal."),
                        fieldWithPath("[*].sex").description("The Sex of the animal."),
                        fieldWithPath("[*].color").description("The Color of the animal."),
                        fieldWithPath("[*].notes").description("Notes on the Animal")
                )));
    }
}

