package com.davingm.sample.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.davingm.sample.model.Product;
import com.davingm.sample.model.ProductDetail;
import com.davingm.sample.repository.ProductRepository;
import com.davingm.sample.request.ProductCreate;
import com.davingm.sample.repository.CategoryRepository;
import com.davingm.sample.repository.ProductDetailRepository;
import com.davingm.sample.model.Category;
import com.davingm.sample.request.ProductUpdate;


@Service
public class ProductService {
    
    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;
    private ProductDetailRepository productDetailRepository;

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository, ProductDetailRepository productDetailRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.productDetailRepository = productDetailRepository;
    }

    public Product createProduct(ProductCreate request) {
        Category category = categoryRepository.findById(request.getCategoryId()).orElseThrow(() -> new RuntimeException("Category tidak ditemukan"));    
        
        Product product = new Product();
        product.setNama(request.getNama());
        product.setDeskripsi(request.getDeskripsi());
        product.setHarga(request.getHarga());
        product.setCategory(category);
        
        product = productRepository.save(product);

        ProductDetail detail = new ProductDetail();
        detail.setGaransi(request.getProductDetail().getGaransi());
        detail.setDeskripsiLengkap(request.getProductDetail().getDeskripsiLengkap());
        detail.setProduct(product);
        detail = productDetailRepository.save(detail);
        
        product.setProductDetail(detail);

        return product;


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
    public Product updateProduct(Long id, ProductUpdate request) {
        Product product = productRepository.findById(id)
        .orElseThrow( () -> new RuntimeException("Product tidak ditemukan"));

        Category category = categoryRepository.findById(request.getCategoryId())
        .orElseThrow(() -> new RuntimeException("Category tidak ditemukan"));

        product.setNama(request.getNama());
        product.setDeskripsi(request.getDeskripsi());
        product.setHarga(request.getHarga());
        product.setCategory(category);



        return productRepository.save(product);
    }

    // hapus data
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

}
