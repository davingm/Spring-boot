package com.davingm.sample.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import com.davingm.sample.model.Product;
import com.davingm.sample.request.ProductCreate;
import com.davingm.sample.request.ProductUpdate;
import com.davingm.sample.response.WebResponse;
import com.davingm.sample.service.ProductService;
import com.davingm.sample.request.AddTagsRequest;
import com.davingm.sample.request.PaymentRequest;
import com.davingm.sample.request.RemoveTagsRequest;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    // CREATE
    @PostMapping
    public WebResponse<Product> createProduct(@Valid @RequestBody ProductCreate request) {
        return WebResponse.<Product>builder()
                .status("00")
                .pesan("Product berhasil ditambahkan")
                .data(productService.createProduct(request))
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
    public WebResponse<Product> updateProduct(@PathVariable Long id, @Valid @RequestBody ProductUpdate request) {
        return WebResponse.<Product>builder()
                .status("00")
                .pesan("Product berhasil diupdate")
                .data(productService.updateProduct(id, request))
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

    @PostMapping("/{id}/tags")
    public WebResponse<Product> addTagToProduct(@PathVariable Long id, @Valid @RequestBody AddTagsRequest request) {
        return WebResponse.<Product>builder()
                .status("00")
                .pesan("Tag berhasil ditambahkan")
                .data(productService.addTagToProduct(id, request))
                .build();
    }

    // Remove TAGS
    @DeleteMapping("/{id}/tags")
    public WebResponse<Product> removeTagFromProduct(@PathVariable Long id, @Valid @RequestBody RemoveTagsRequest request) {
        return WebResponse.<Product>builder()
                .status("00")
                .pesan("Tag berhasil dihapus")
                .data(productService.removeTagFromProduct(id, request))
                .build();
    }

    @GetMapping("/filter/category-min-price")
    public WebResponse<List<Product>> getProductsByCategoryAndMinPrice(
            @RequestParam Long categoryId,
            @RequestParam Double minPrice) {
        return WebResponse.<List<Product>>builder()
                .status("00")
                .pesan("Berhasil mengambil produk berdasarkan kategori dan harga minimum")
                .data(productService.getProductsByCategoryAndMinPrice(categoryId, minPrice))
                .build();
    }

    @GetMapping("/filter/category/{categoryName}/min-price/{minPrice}")
    public WebResponse<List<Product>> getProductsByCategoryNameAndMinPrice(
            @PathVariable String categoryName,
            @PathVariable Double minPrice) {
        return WebResponse.<List<Product>>builder()
                .status("00")
                .pesan("Berhasil mengambil produk untuk kategori " + categoryName + " dengan harga minimum " + minPrice)
                .data(productService.getProductsByCategoryNameAndMinPrice(categoryName, minPrice))
                .build();
    }

    @GetMapping("/filter/tag-category")
    public WebResponse<List<Product>> getProductsByTagAndCategory(
            @RequestParam Long tagId,
            @RequestParam Long categoryId) {
        return WebResponse.<List<Product>>builder()
                .status("00")
                .pesan("Berhasil mengambil produk berdasarkan tag dan kategori")
                .data(productService.getProductsByTagAndCategory(tagId, categoryId))
                .build();
    }

    @GetMapping("/filter/tag/{tagId}/category/{categoryName}")
    public WebResponse<List<Product>> getProductsByTagAndCategoryName(
            @PathVariable Long tagId,
            @PathVariable String categoryName) {
        return WebResponse.<List<Product>>builder()
                .status("00")
                .pesan("Berhasil mengambil produk dengan tag " + tagId + " dan kategori " + categoryName)
                .data(productService.getProductsByTagAndCategoryName(tagId, categoryName))
                .build();
    }

    @GetMapping("/filter/price-range")
    public WebResponse<List<Product>> getProductsByPriceRange(
            @RequestParam Double minPrice,
            @RequestParam Double maxPrice) {
        return WebResponse.<List<Product>>builder()  
                .status("00")
                .pesan("Berhasil mengambil produk berdasarkan rentang harga")
                .data(productService.getProductsByPriceRange(minPrice, maxPrice))
                .build();
    }

    @GetMapping("/filter/price-range/{minPrice}/{maxPrice}")
    public WebResponse<List<Product>> getProductsByPriceRangePath(
            @PathVariable Double minPrice,
            @PathVariable Double maxPrice) {
        return WebResponse.<List<Product>>builder()
                .status("00")
                .pesan("Berhasil mengambil produk dengan harga antara " + minPrice + " dan " + maxPrice)
                .data(productService.getProductsByPriceRange(minPrice, maxPrice))
                .build();
    }


    // Proses Pembelian 
    @PostMapping("/{productId}/buy/product")
    public WebResponse<Product> prosesPembelian(@PathVariable Long productId, @Valid @RequestBody PaymentRequest request) {
        return WebResponse.<Product>builder()
                .status("00")
                .pesan("Pembelian berhasil")
                .data(productService.prosesPembelian(productId, request))
                .build();
    }       
    


    
}
