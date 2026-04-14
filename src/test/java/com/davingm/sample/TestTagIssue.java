package com.davingm.sample;

import com.davingm.sample.model.Category;
import com.davingm.sample.model.Product;
import com.davingm.sample.model.ProductDetail;
import com.davingm.sample.model.Tag;
import com.davingm.sample.repository.CategoryRepository;
import com.davingm.sample.repository.ProductRepository;
import com.davingm.sample.repository.TagRepository;
import com.davingm.sample.request.AddTagsRequest;
import com.davingm.sample.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class TestTagIssue {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @org.springframework.transaction.annotation.Transactional
    public void test() throws Exception {
        Category cat = new Category();
        cat.setName("Motor");
        cat = categoryRepository.save(cat);

        Product prod = new Product();
        prod.setNama("yamaha");
        prod.setDeskripsi("motor wok wok");
        prod.setHarga(15000.0);
        prod.setCategory(cat);
        
        ProductDetail detail = new ProductDetail();
        detail.setGaransi("17 tahun");
        detail.setDeskripsiLengkap("mobil");
        detail.setProduct(prod);
        prod.setProductDetail(detail);

        prod = productRepository.save(prod);

        Tag tag = new Tag();
        tag.setName("baru");
        tag = tagRepository.save(tag);

        System.out.println("---- BEFORE ADDING TAG ----");
        System.out.println("Product tags: " + prod.getTags());

        AddTagsRequest req = new AddTagsRequest();
        req.setTagsId(List.of(tag.getId()));

        Product updatedProd = productService.addTagToProduct(prod.getId(), req);

        System.out.println("---- AFTER ADDING TAG ----");
        System.out.println(objectMapper.writeValueAsString(updatedProd));
        
        Product fetched = productRepository.findById(prod.getId()).get();
        System.out.println("---- FETCHED AGAIN ----");
        System.out.println(objectMapper.writeValueAsString(fetched));
    }
}
