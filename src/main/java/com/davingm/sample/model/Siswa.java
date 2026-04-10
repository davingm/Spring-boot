package com.davingm.sample.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "siswa")
public class Siswa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "NIS tidak boleh kosong")
    private String nis;

    @NotBlank(message = "Nama tidak boleh kosong")
    private String nama;

    @NotBlank(message = "Kelas tidak boleh kosong")
    private String kelas;

    @NotBlank(message = "Jenis Kelamin tidak boleh kosong")
    private String jenisKelamin;

    private String alamat;
    private String hobi;
    private String warnaKesukaan;

    @Column(length = 15)
    @NotBlank(message = "No Telepon tidak boleh kosong")
    private String noTelepon;

    @Column(length = 50)
    @NotBlank(message = "Tempat Lahir tidak boleh kosong")
    private String tempatLahir;

    @Column(length = 5)
    @NotBlank(message = "Golongan Darah tidak boleh kosong")
    private String golonganDarah;
}