package com.example.petstore.restdocs;

import com.example.petstore.client.ShelternetClient;
import com.example.petstore.controllers.AnimalController;
import com.example.petstore.model.AnimalEntity;
import com.example.petstore.model.AnimalIds;
import com.example.petstore.model.AnimalResponseFromShelternet;
import com.example.petstore.model.AnimalResponseFromShelternetDTO;
import com.example.petstore.services.AnimalService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

    @Test
    public void whenRequestAnimalsFromShelternet_AnimalsShouldTransferINThePetStore() throws Exception {
        AnimalIds animalIds = new AnimalIds(List.of(1L));

        when(animalService.requestAnimalsFromShelternet(animalIds)).thenReturn(new AnimalResponseFromShelternet(List.of(new AnimalResponseFromShelternetDTO(1L, "TEST", "DOG",
                LocalDate.of(2012, 4, 2), "M", "RED"))));

        mockMvc.perform(post("/animals/request")
                .content(objectMapper.writeValueAsString(animalIds))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document("request-animals-from-shelternet", requestFields(
                        fieldWithPath("animalIds").description("Requested animal ids."))));
        //TODO see why this doesn't work.
//                ), responseFields(
//                        fieldWithPath("$.animals.[*].id").description("Animal ID."),
//                        fieldWithPath("$.animals.[*].name").description("The Name of the animal."),
//                        fieldWithPath("$.animals.[*].species").description("The Species of the animal."),
//                        fieldWithPath("$.animals.[*].birthDate").description("The BirthDate of the animal."),
//                        fieldWithPath("$.animals.[*].sex").description("The Sex of the animal."),
//                        fieldWithPath("$.animals.[*].color").description("The Color of the animal."))));
    }
}

