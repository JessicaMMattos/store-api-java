package com.example.store.services;

import com.example.store.models.Product;
import com.example.store.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public List<Product> findAll() {
        return repository.findAll();
    }

    public Optional<Product> findById(Long id) {
        return repository.findById(id);
    }

    public Product save(Product product) {
        return repository.save(product);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public String sellProduct(Long id, int quantityToSell) {
        Product product = repository.findById(id).orElse(null);
        if (product == null) {
            return "Product not found.";
        }

        if (product.getQuantity() < quantityToSell) {
            return "Product '" + product.getName() + "' has no available units.";
        }

        product.setQuantity(product.getQuantity() - quantityToSell);
        repository.save(product);
        return "Sale completed successfully.";
    }
}
