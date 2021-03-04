package com.example.petstore.services;

import com.example.petstore.TestUtils.Mocks;
import com.example.petstore.client.ShelternetClient;
import com.example.petstore.model.AnimalIds;
import com.example.petstore.model.AnimalResponseFromShelternet;
import com.example.petstore.model.AnimalResponseFromShelternetDTO;
import com.example.petstore.model.entities.AnimalEntity;
import com.example.petstore.model.entities.ItemEntity;
import com.example.petstore.repository.AnimalRepository;
import com.example.petstore.repository.ItemRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
public class ItemServiceTests {

    @Mock
    private ItemRepository itemRepository;

    @InjectMocks
    private ItemService itemService;

    @Test
    public void whenPetStoreAddItems_thenTheStoreCatalogShouldShowWithZeroStock() {
        when(itemRepository.save(any())).thenReturn(Mocks.getItem());

        ItemEntity item = itemService.addItemInStock(Mocks.getItem());

        verify(itemRepository, times(1)).save(any());

        assertEquals(Mocks.getItem(), item);
    }
}
