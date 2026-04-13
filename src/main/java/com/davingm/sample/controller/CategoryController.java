package com.davingm.sample.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.davingm.sample.model.Category;
import com.davingm.sample.response.WebResponse;
import com.davingm.sample.service.CategoryService;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    // Ganti ke  pesan ke bahasa indonesia
    
    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public WebResponse<Category> createCategory(@RequestBody Category category) {
        Category createdCategory = categoryService.createCategory(category);
        return WebResponse.<Category>builder()
                .status("00")
                .pesan("Kategori berhasil ditambahkan")
                .data(createdCategory)
                .build();
    }

    @GetMapping
    public WebResponse<List<Category>> getAllCategories() {
        List<Category> categories = categoryService.getAllCategories();
        return WebResponse.<List<Category>>builder()
                .status("00")
                .pesan("Kategori berhasil diambil")
                .data(categories)
                .build();
    }

    @GetMapping("/{id}")
    public WebResponse<Category> getCategoryById(@PathVariable Long id) {
        Category category = categoryService.getCategoryById(id);
        return WebResponse.<Category>builder()
                .status("00")
                .pesan("Kategori berhasil diambil")
                .data(category)
                .build();
    }

    @PutMapping("/{id}")
    public WebResponse<Category> updateCategory(@PathVariable Long id, @RequestBody Category category) {
        Category updatedCategory = categoryService.updateCategory(id, category);
        return WebResponse.<Category>builder()
                .status("00")
                .pesan("Kategori berhasil diupdate")
                .data(updatedCategory)
                .build();
    }

    @DeleteMapping("/{id}")
    public WebResponse<Category> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return WebResponse.<Category>builder()
                .status("00")
                .pesan("Kategori berhasil dihapus")
                .build();
    }

}
