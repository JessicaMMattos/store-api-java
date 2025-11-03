package com.example.store.controllers;

import com.example.store.models.Product;
import com.example.store.services.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductControllerTest {

    private ProductService service;
    private ProductController controller;

    @BeforeEach
    void setUp() {
        service = Mockito.mock(ProductService.class);
        controller = new ProductController(service);
    }

    @Test
    void shouldReturnAllProducts() {
        List<Product> products = Arrays.asList(
                new Product(1L, "Phone", "Smartphone", 1000.0, 5),
                new Product(2L, "Laptop", "Notebook", 3000.0, 2)
        );
        when(service.findAll()).thenReturn(products);

        ResponseEntity<List<Product>> response = controller.getAll();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(2, response.getBody().size());
    }

    @Test
    void shouldReturnProductById() {
        Product product = new Product(1L, "Phone", "Smartphone", 1000.0, 5);
        when(service.findById(1L)).thenReturn(Optional.of(product));

        ResponseEntity<Product> response = controller.getById(1L);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Phone", response.getBody().getName());
    }

    @Test
    void shouldReturnNotFoundWhenProductDoesNotExist() {
        when(service.findById(1L)).thenReturn(Optional.empty());

        ResponseEntity<Product> response = controller.getById(1L);

        assertEquals(404, response.getStatusCodeValue());
        assertNull(response.getBody());
    }

    @Test
    void shouldCreateProduct() {
        Product product = new Product(null, "Tablet", "Android tablet", 800.0, 10);
        Product saved = new Product(1L, "Tablet", "Android tablet", 800.0, 10);
        when(service.save(product)).thenReturn(saved);

        ResponseEntity<Product> response = controller.create(product);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1L, response.getBody().getId());
    }

    @Test
    void shouldSellProduct() {
        when(service.sellProduct(1L, 3)).thenReturn("Sold 3 units of Phone. Remaining: 2");

        ResponseEntity<String> response = controller.sellProduct(1L, 3);

        assertEquals(200, response.getStatusCodeValue());
        assertTrue(response.getBody().contains("Sold"));
    }
}
