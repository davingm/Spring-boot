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
import com.davingm.sample.repository.TagRepository;
import com.davingm.sample.request.AddTagsRequest;
import com.davingm.sample.request.PaymentRequest;
import com.davingm.sample.request.RemoveTagsRequest;
import com.davingm.sample.model.Tag;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Service
public class ProductService {

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;
    private ProductDetailRepository productDetailRepository;
    private TagRepository tagRepository;

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository,
            ProductDetailRepository productDetailRepository, TagRepository tagRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.productDetailRepository = productDetailRepository;
        this.tagRepository = tagRepository;
    }

    // create product
    public Product createProduct(ProductCreate request) {
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category tidak ditemukan"));

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

    public Product updateProduct(Long id, ProductUpdate request) {
        // check product
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product tidak ditemukan"));

        // check category
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category tidak ditemukan"));

        // update product
        product.setNama(request.getNama());
        product.setDeskripsi(request.getDeskripsi());
        product.setHarga(request.getHarga());
        product.setCategory(category);

        // Jika detail dari request tidak Null, maka update ProductDetail
        // get langsung by data productdetail = product.getProductDetail()
        if (request.getProductDetail() != null) {
            ProductDetail detail = product.getProductDetail();

            if (detail == null) {
                detail = new ProductDetail();
                detail.setProduct(product);
            }

            detail.setGaransi(request.getProductDetail().getGaransi());
            detail.setDeskripsiLengkap(request.getProductDetail().getDeskripsiLengkap());

            // Karena cascade = CascadeType.ALL, cukup save dari product saja
            product.setProductDetail(detail);
        }

        return productRepository.save(product);
    }

    // hapus data
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public Product addTagToProduct(Long id, AddTagsRequest request) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product tidak ditemukan"));

        List<Tag> tags = tagRepository.findAllById(request.getTagsId());

        if (tags.isEmpty() || tags.size() != request.getTagsId().size()) {
            throw new RuntimeException("Satu atau lebih Tag tidak ditemukan");
        }

        if (product.getTags() == null) {
            product.setTags(new java.util.ArrayList<>());
        }
        
        for (Tag tag : tags) {
            if (!product.getTags().contains(tag)) {
                product.getTags().add(tag);
            }
        }
        
        return productRepository.save(product);
    } 


    
    public Product removeTagFromProduct(Long id, RemoveTagsRequest request) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product tidak ditemukan"));

        List<Tag> tags = tagRepository.findAllById(request.getTagsId());

        if (product.getTags() != null && !tags.isEmpty()) {
            for (Tag tag : tags) {
                product.getTags().remove(tag);
            }
        }
        
        return productRepository.save(product);
    }

    public List<Product> getProductsByCategoryAndMinPrice(Long categoryId, Double minPrice) {
        return productRepository.findByCategory_IdAndHargaGreaterThanEqual(categoryId, minPrice);
    }

    public List<Product> getProductsByCategoryNameAndMinPrice(String categoryName, Double minPrice) {
        return productRepository.findByCategory_NameIgnoreCaseAndHargaGreaterThanEqual(categoryName, minPrice);
    }

    public List<Product> getProductsByTagAndCategory(Long tagId, Long categoryId) {
        return productRepository.findByTagIdAndCategoryId(tagId, categoryId);
    }

    public List<Product> getProductsByTagAndCategoryName(Long tagId, String categoryName) {
        return productRepository.findByTagIdAndCategoryName(tagId, categoryName);
    }

    public List<Product> getProductsByPriceRange(Double minPrice, Double maxPrice) {
        if(minPrice > maxPrice) {
            throw new RuntimeException("Harga minimum tidak boleh lebih besar dari harga maksimum");
        }
        return productRepository.findByHargaBetween(minPrice, maxPrice);
    }



    
    @Transactional
    public Product prosesPembelian(Long productId, PaymentRequest request) {

      
        // 1. Kurangi Stoack Produk
        Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product tidak ditemukan"));
        product.setStok(product.getStok() - 1);
        productRepository.save(product);


        // Simulasi Error yang tidak terduga
        // jika kode di bawah ini eror stok produck tidak jadi berkurang di database
        if(request.getAmount() < product.getHarga()) {
            throw new RuntimeException("Uang tidak cukup");
        }

        return product;
    }   
}
