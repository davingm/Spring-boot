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

    @NotBlank(message = "No Telepon tidak boleh kosong")
    @Size(max = 15, message = "No Telepon maksimal 15 digit")
    private String noTelepon;

    @NotBlank(message = "Tempat Lahir tidak boleh kosong")
    private String tempatLahir;

    @NotBlank(message = "Golongan Darah tidak boleh kosong")
    @Size(max = 5, message = "Golongan Darah maksimal 5 digit")
    private String golonganDarah;
}