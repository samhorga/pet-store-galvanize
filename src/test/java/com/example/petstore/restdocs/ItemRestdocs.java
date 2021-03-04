package com.example.petstore.restdocs;

import com.example.petstore.controllers.ItemController;
import com.example.petstore.model.entities.ItemEntity;
import com.example.petstore.services.ItemService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ItemController.class)
@AutoConfigureMockMvc
@AutoConfigureRestDocs
@ActiveProfiles("test")
public class ItemRestdocs {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ItemService itemService;

    @Test
    public void whenPetStoreAddItems_thenTheStoreCatalogShouldShowWithZeroStock() throws Exception {
        ItemEntity item = new ItemEntity("FOOD", "DOG", "LAYS", "CHIPS", "THIS IS CHIPS FOR DOG", 14.59);
        item.setSku(1L);

        when(itemService.addItemInStock(any())).thenReturn(item);

        mockMvc.perform(post("/items")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(item)))
                .andExpect(status().isOk())
                .andDo(document("add-item",
                        requestFields(
                                fieldWithPath("sku").description("The ID of the item."),
                                fieldWithPath("category").description("Category of the Item."),
                                fieldWithPath("animalType").description("The Type of the Item."),
                                fieldWithPath("brand").description("The Brand of the Item."),
                                fieldWithPath("name").description("The name of the item."),
                                fieldWithPath("description").description("Description of the item."),
                                fieldWithPath("price").description("Price of the item."),
                                fieldWithPath("quantity").description("Quantity of this item.")),
                        responseFields(
                                fieldWithPath("sku").description("The ID of the item."),
                                fieldWithPath("category").description("Category of the Item."),
                                fieldWithPath("animalType").description("The Type of the Item."),
                                fieldWithPath("brand").description("The Brand of the Item."),
                                fieldWithPath("name").description("The name of the item."),
                                fieldWithPath("price").description("Price of the item."),
                                fieldWithPath("description").description("Description of the item."),
                                fieldWithPath("quantity").description("Quantity of this item.")
                        )));

    }
}

