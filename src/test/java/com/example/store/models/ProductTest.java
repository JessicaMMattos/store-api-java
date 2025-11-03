package com.example.store.models;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    @Test
    void testGettersAndSetters() {
        Product product = new Product();
        product.setId(1L);
        product.setName("Phone");
        product.setDescription("Smartphone");
        product.setPrice(1000.0);
        product.setQuantity(5);

        assertEquals(1L, product.getId());
        assertEquals("Phone", product.getName());
        assertEquals("Smartphone", product.getDescription());
        assertEquals(1000.0, product.getPrice());
        assertEquals(5, product.getQuantity());
    }

    @Test
    void testConstructor() {
        Product product = new Product(2L, "Laptop", "Notebook", 3000.0, 2);

        assertEquals(2L, product.getId());
        assertEquals("Laptop", product.getName());
        assertEquals("Notebook", product.getDescription());
        assertEquals(3000.0, product.getPrice());
        assertEquals(2, product.getQuantity());
    }
}
