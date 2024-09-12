package com.example.productapi.controller;

import com.example.productapi.model.Product;
import com.example.productapi.repository.ProductRepository;
import com.example.productapi.dto.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    private final Path fileStorageLocation = Paths.get("uploaded-images").toAbsolutePath().normalize();

    public ProductController() {
        try {
            Files.createDirectories(fileStorageLocation);
        } catch (Exception ex) {
            throw new RuntimeException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        return productRepository.findById(id)
                .map(product -> ResponseEntity.ok().body(product))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Product>> getProductsByCategory(@PathVariable String category) {
        List<Product> products = productRepository.findByCategory(category);
        if (products.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(products);
        }
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody ProductDto productDto) {
        String imageUrl = productDto.getImageUrl();

        if (productDto.getImage() != null && !productDto.getImage().isEmpty()) {
            // Upload the image and get its URL
            try {
                String fileName = StringUtils.cleanPath(productDto.getImage().getOriginalFilename());
                Path targetLocation = fileStorageLocation.resolve(fileName);
                Files.copy(productDto.getImage().getInputStream(), targetLocation);
                imageUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                        .path("/products/images/")
                        .path(fileName)
                        .toUriString();
            } catch (IOException ex) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
            }
        }

        // Create the product
        Product product = new Product();
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setCategory(productDto.getCategory());
        product.setStock(productDto.getStock());
        product.setImageUrl(imageUrl);
        product.setCreatedAt(LocalDateTime.now());
        product.setUpdatedAt(LocalDateTime.now());

        Product savedProduct = productRepository.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody ProductDto productDto) {
        return productRepository.findById(id)
                .map(existingProduct -> {
                    existingProduct.setName(productDto.getName());
                    existingProduct.setPrice(productDto.getPrice());
                    existingProduct.setDescription(productDto.getDescription());
                    existingProduct.setCategory(productDto.getCategory());
                    existingProduct.setStock(productDto.getStock());
                    existingProduct.setUpdatedAt(LocalDateTime.now());
                    Product updatedProduct = productRepository.save(existingProduct);
                    return ResponseEntity.ok().body(updatedProduct);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
