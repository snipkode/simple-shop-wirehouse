package com.wirehouse.simpleshop;

import com.wirehouse.simpleshop.dto.ItemRequest;
import com.wirehouse.simpleshop.dto.VariantRequest;
import com.wirehouse.simpleshop.repository.ItemRepository;
import com.wirehouse.simpleshop.service.WarehouseService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(properties = {
    "spring.jpa.hibernate.ddl-auto=create-drop",
    "spring.datasource.url=jdbc:h2:mem:testdb"
})
class WarehouseServiceTest {

    @Autowired
    private WarehouseService warehouseService;
    
    @Autowired
    private ItemRepository itemRepository;

    @Test
    void testCreateItemWithVariants() {
        // Arrange
        ItemRequest itemRequest = new ItemRequest();
        itemRequest.setName("Test Laptop");
        itemRequest.setDescription("A high-performance test laptop");
        itemRequest.setCategory("Electronics");
        itemRequest.setActive(true);
        
        VariantRequest variant1 = new VariantRequest();
        variant1.setName("i7/16GB");
        variant1.setPrice(1200.00);
        variant1.setInitialStock(5);
        variant1.setActive(true);
        
        VariantRequest variant2 = new VariantRequest();
        variant2.setName("i5/8GB");
        variant2.setPrice(800.00);
        variant2.setInitialStock(10);
        variant2.setActive(true);
        
        itemRequest.setVariants(Arrays.asList(variant1, variant2));

        // Act
        var response = warehouseService.createItem(itemRequest);

        // Assert
        assertNotNull(response);
        assertEquals("Test Laptop", response.getName());
        assertEquals("Electronics", response.getCategory());
        assertEquals(2, response.getVariants().size());
        
        var savedItem = itemRepository.findById(response.getId()).orElse(null);
        assertNotNull(savedItem);
        assertEquals("Test Laptop", savedItem.getName());
    }

    @Test
    void testInsufficientStockReservation() {
        // Arrange
        ItemRequest itemRequest = new ItemRequest();
        itemRequest.setName("Test Product");
        itemRequest.setCategory("Test");
        itemRequest.setActive(true);
        
        VariantRequest variant = new VariantRequest();
        variant.setName("Standard");
        variant.setPrice(10.00);
        variant.setInitialStock(1); // Only 1 in stock
        variant.setActive(true);
        
        itemRequest.setVariants(Arrays.asList(variant));
        
        var itemResponse = warehouseService.createItem(itemRequest);
        Long variantId = itemResponse.getVariants().get(0).getId();

        // Act & Assert - Should succeed for 1 item
        assertTrue(warehouseService.reserveStock(variantId, 1));
        
        // Try to reserve more than available - should throw exception
        assertThrows(RuntimeException.class, () -> {
            warehouseService.reserveStock(variantId, 1); // Try to reserve 1 more when none left
        });
    }
}