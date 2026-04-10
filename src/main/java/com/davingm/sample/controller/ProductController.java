package com.davingm.sample.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;


import com.davingm.sample.model.Product;
import com.davingm.sample.response.WebResponse;
import com.davingm.sample.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    // CREATE
    @PostMapping
    public WebResponse<Product> createProduct(@Valid @RequestBody Product product) {
        return WebResponse.<Product>builder()
                .status("00")
                .pesan("Product berhasil ditambahkan")
                .data(productService.saveProduct(product))
                .build();
    }

    // READ ALL
    @GetMapping
    public WebResponse<List<Product>> getAllProducts() {
        return WebResponse.<List<Product>>builder()
                .status("00")
                .pesan("Berhasil mengambil semua data produk")
                .data(productService.getAllProducts())
                .build();
    }

    // READ BY ID
    @GetMapping("/{id}")
    public WebResponse<Product> getProductById(@PathVariable Long id) {
        return productService.getProductById(id)
                .map(product -> WebResponse.<Product>builder()
                        .status("00")
                        .pesan("Berhasil mengambil data produk")
                        .data(product)
                        .build())
                .orElseThrow(() -> new RuntimeException("Product tidak ditemukan"));
    }

    // UPDATE
    @PutMapping("/{id}")
    public WebResponse<Product> updateProduct(@PathVariable Long id, @Valid @RequestBody Product dataBaru) {
        return WebResponse.<Product>builder()
                .status("00")
                .pesan("Product berhasil diupdate")
                .data(productService.updateProduct(id, dataBaru))
                .build();
    }

    // DELETE
    @DeleteMapping("/{id}")
    public WebResponse<String> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return WebResponse.<String>builder()
                .status("00")
                .pesan("Product dengan ID " + id + " berhasil dihapus")
                .data(null)
                .build();
    }
}
