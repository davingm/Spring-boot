package com.davingm.sample.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProductCreate {
    
    @NotBlank(message = "Nama tidak boleh kosong")
    private String nama;

    @NotBlank(message = "Deskripsi tidak boleh kosong")
    private String deskripsi;

    @Min(value = 500, message = "Harga harus minimal 500")
    @NotNull(message = "Harga tidak boleh kosong")
    private double harga;

    @NotNull(message = "Category tidak boleh kosong")
    private Long categoryId;
}
