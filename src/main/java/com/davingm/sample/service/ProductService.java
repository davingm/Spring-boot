package com.davingm.sample.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.davingm.sample.model.Product;
import com.davingm.sample.repository.ProductRepository;

@Service
public class ProductService {
    
    @Autowired
    private ProductRepository productRepository;

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    // Mengambil semua data product
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // Menagambil data berdasarkan id
    public java.util.Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    // Mengubah data Product
    public Product updateProduct(Long id, Product dataBaru) {
        Product product = productRepository.findById(id)
        .orElseThrow( () -> new RuntimeException("Product tidak ditemukan"));

        product.setNama(dataBaru.getNama());
        product.setDeskripsi(dataBaru.getDeskripsi());
        product.setHarga(dataBaru.getHarga());

        return productRepository.save(product);
    }

    // hapus data
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

}
