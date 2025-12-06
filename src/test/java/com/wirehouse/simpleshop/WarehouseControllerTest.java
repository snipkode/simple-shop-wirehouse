package com.wirehouse.simpleshop;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wirehouse.simpleshop.dto.ItemRequest;
import com.wirehouse.simpleshop.dto.VariantRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureWebMvc
@TestPropertySource(properties = {
    "spring.jpa.hibernate.ddl-auto=create-drop",
    "spring.datasource.url=jdbc:h2:mem:testdb"
})
class WarehouseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCreateItemEndpoint() throws Exception {
        // Arrange
        ItemRequest itemRequest = new ItemRequest();
        itemRequest.setName("Test Product");
        itemRequest.setDescription("Test product description");
        itemRequest.setCategory("Test Category");
        itemRequest.setActive(true);
        
        VariantRequest variant = new VariantRequest();
        variant.setName("Test Variant");
        variant.setPrice(29.99);
        variant.setInitialStock(5);
        variant.setActive(true);
        
        itemRequest.setVariants(Arrays.asList(variant));

        // Act & Assert
        mockMvc.perform(post("/api/warehouse/items")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(itemRequest)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void testGetAllItemsEndpoint() throws Exception {
        // Act & Assert
        mockMvc.perform(get("/api/warehouse/items"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
}