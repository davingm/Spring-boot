package com.davingm.sample.model;

import java.util.List;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nama tidak boleh kosong")
    private String nama;

    private String deskripsi;

    @Column(name = "harga")
    @Min(value = 500, message = "Harga harus minimal 500")
    @NotNull(message = "Harga tidak boleh kosong")
    private double harga;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL)
    private ProductDetail productDetail;

    // Stok
    @Column(name = "stok")
    @Min(value = 0, message = "Stok harus minimal 0")
    @NotNull(message = "Stok tidak boleh kosong")
    private int stok;


    @ManyToMany
    @JoinTable(
        name = "product_tags",
        joinColumns = @JoinColumn(name = "product_id"),
        inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private List<Tag> tags;
}




