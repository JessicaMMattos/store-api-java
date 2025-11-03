package com.example.store.services;

import com.example.store.models.Product;
import com.example.store.repositories.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceTest {

    private ProductRepository repository;
    private ProductService service;

    @BeforeEach
    void setUp() {
        repository = Mockito.mock(ProductRepository.class);
        service = new ProductService(repository);
    }

    @Test
    void shouldReturnAllProducts() {
        List<Product> products = Arrays.asList(
                new Product(1L, "Phone", "Smartphone", 1000.0, 5),
                new Product(2L, "Laptop", "Notebook", 3000.0, 2)
        );

        when(repository.findAll()).thenReturn(products);

        List<Product> result = service.findAll();

        assertEquals(2, result.size());
        verify(repository, times(1)).findAll();
    }

    @Test
    void shouldReturnProductById() {
        Product product = new Product(1L, "Phone", "Smartphone", 1000.0, 5);
        when(repository.findById(1L)).thenReturn(Optional.of(product));

        Optional<Product> result = service.findById(1L);

        assertTrue(result.isPresent());
        assertEquals("Phone", result.get().getName());
        verify(repository, times(1)).findById(1L);
    }

    @Test
    void shouldSaveProduct() {
        Product product = new Product(null, "Tablet", "Android tablet", 800.0, 10);
        Product saved = new Product(1L, "Tablet", "Android tablet", 800.0, 10);

        when(repository.save(product)).thenReturn(saved);

        Product result = service.save(product);

        assertEquals(1L, result.getId());
        verify(repository, times(1)).save(product);
    }

    @Test
    void shouldDeleteProduct() {
        doNothing().when(repository).deleteById(1L);

        service.delete(1L);

        verify(repository, times(1)).deleteById(1L);
    }

    @Test
    void shouldSellProductSuccessfully() {
        Product product = new Product(1L, "Phone", "Smartphone", 1000.0, 5);
        when(repository.findById(1L)).thenReturn(Optional.of(product));
        when(repository.save(product)).thenReturn(product);

        String message = service.sellProduct(1L, 3);

        assertEquals("Sale completed successfully.", message);
        assertEquals(2, product.getQuantity()); // quantidade atualizada
        verify(repository, times(1)).save(product);
    }

    @Test
    void shouldReturnNotFoundWhenSellingNonExistingProduct() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        String message = service.sellProduct(1L, 3);

        assertEquals("Product not found.", message);
        verify(repository, never()).save(any());
    }

    @Test
    void shouldReturnNoUnitsAvailableWhenSellingTooMuch() {
        Product product = new Product(1L, "Phone", "Smartphone", 1000.0, 2);
        when(repository.findById(1L)).thenReturn(Optional.of(product));

        String message = service.sellProduct(1L, 5);

        assertEquals("Product 'Phone' has no available units.", message);
        assertEquals(2, product.getQuantity()); // quantidade não mudou
        verify(repository, never()).save(any());
    }
}
