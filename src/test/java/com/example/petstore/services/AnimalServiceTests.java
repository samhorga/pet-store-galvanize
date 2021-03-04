package com.example.petstore.services;

import com.example.petstore.TestUtils.Mocks;
import com.example.petstore.client.ShelternetClient;
import com.example.petstore.model.AnimalEntity;
import com.example.petstore.model.AnimalIds;
import com.example.petstore.model.AnimalResponseFromShelternet;
import com.example.petstore.model.AnimalResponseFromShelternetDTO;
import com.example.petstore.repository.AnimalRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
public class AnimalServiceTests {

    @Mock
    private AnimalRepository animalRepository;

    @Mock
    private ShelternetClient shelternetClient;

    @InjectMocks
    private AnimalService animalService;

    @Test
    public void WhenThereAreAnimalsInThePetStore_thenShouldReturnAnimals() {
        when(animalRepository.findAll()).thenReturn(Mocks.getAnimals());

        List<AnimalEntity> animals = animalService.getAnimals();

        assertEquals(Mocks.getAnimals(), animals);
    }

    @Test
    public void whenRequestAnimalsFromShelternet_AnimalsShouldTransferINThePetStore() {
        AnimalIds animalIds = new AnimalIds(List.of(1L));
        AnimalResponseFromShelternet animalResponseFromShelternet = new AnimalResponseFromShelternet(List.of(new AnimalResponseFromShelternetDTO(1L, "TEST", "DOG",
                        LocalDate.of(2012, 4, 2), "M", "RED")));
        when(shelternetClient.requestAnimals(any())).thenReturn(animalResponseFromShelternet);
        AnimalResponseFromShelternet actualAnimalResponseFromShelternet = animalService.requestAnimalsFromShelternet(animalIds);

        assertEquals("TEST", actualAnimalResponseFromShelternet.getAnimals().get(0).getName());
        assertEquals("DOG", animalResponseFromShelternet.getAnimals().get(0).getSpecies());
        assertEquals(LocalDate.of(2012, 4, 2), animalResponseFromShelternet.getAnimals().get(0).getBirthDate());
        assertEquals("M", animalResponseFromShelternet.getAnimals().get(0).getSex());
        assertEquals("RED", animalResponseFromShelternet.getAnimals().get(0).getColor());
    }
}
