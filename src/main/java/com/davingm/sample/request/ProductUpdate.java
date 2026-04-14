package com.davingm.sample.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProductUpdate {
    
    @NotBlank(message = "Nama tidak boleh kosong")
    private String nama;

    @NotBlank(message = "Deskripsi tidak boleh kosong")
    private String deskripsi;

    @Min(value = 500, message = "Harga harus minimal 500")
    @NotNull(message = "Harga tidak boleh kosong")
    private double harga;

    @NotNull(message = "Category tidak boleh kosong")
    @JsonProperty("category_id")
    private Long categoryId;

    @JsonProperty("product_detail")
    private ProductDetailUpdate productDetail;

    @Data
    public static class ProductDetailUpdate {
        @NotBlank(message = "Garansi tidak boleh kosong")
        private String garansi;
        
        @NotBlank(message = "Deskripsi lengkap tidak boleh kosong")
        private String deskripsiLengkap;

    }

    
}   

