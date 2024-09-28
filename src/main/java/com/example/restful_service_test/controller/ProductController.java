package com.example.restful_service_test.controller;

import com.example.restful_service_test.controller.model.ProductID;
import com.example.restful_service_test.model.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class ProductController {

    // In-memory list to store products
    private final List<Product> productList = new ArrayList<>();

    @PostMapping("/product")
    public ResponseEntity<ProductID> createProduct(@RequestBody final Product product) {
        // Assign a unique ID to the product
        product.setId(UUID.randomUUID().toString());

        // Add the product to the in-memory list
        productList.add(product);

        ProductID result = new ProductID(product.getId());
        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(result);
    }

    @GetMapping("/product")
    public List<Product> getAllProducts() {
        // Return all products from the in-memory list
        return productList;
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable final String id) {
        // Search for the product by ID in the in-memory list
        Optional<Product> product = productList.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();

        // Return the product if found, otherwise return 404 (Not Found)
        if (product.isPresent()) {
            return ResponseEntity.ok(product.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PatchMapping("/product")
    public ResponseEntity<Void> patchProduct(@RequestBody final Product product) {
        // Find the product in the list by ID and update its fields
        Optional<Product> existingProduct = productList.stream()
                .filter(p -> p.getId().equals(product.getId()))
                .findFirst();

        if (existingProduct.isPresent()) {
            // Update product details
            existingProduct.get().setDescription(product.getDescription());
            existingProduct.get().setPrice(product.getPrice());
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable final String id) {
        // Remove the product from the list if it exists
        boolean removed = productList.removeIf(p -> p.getId().equals(id));

        if (removed) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}


