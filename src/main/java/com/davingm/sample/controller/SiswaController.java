package com.davingm.sample.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.davingm.sample.model.Siswa;
import com.davingm.sample.response.WebResponse;
import com.davingm.sample.service.SiswaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/siswa")
public class SiswaController {

    @Autowired
    private SiswaService siswaService;

    // Create
    @PostMapping
    public WebResponse<Siswa> createSiswa(@Valid @RequestBody Siswa siswa) {
        return WebResponse.<Siswa>builder()
                .status("00")
                .pesan("Siswa berhasil ditambahkan")
                .data(siswaService.saveSiswa(siswa))
                .build();
    }

    // Read All
    @GetMapping
    public WebResponse<List<Siswa>> getAllSiswa() {
        return WebResponse.<List<Siswa>>builder()
                .status("00")
                .pesan("Berhasil mengambil semua data siswa")
                .data(siswaService.getAllSiswa())
                .build();
    }

    // Read By ID
    @GetMapping("/{id}")
    public WebResponse<Siswa> getSiswaById(@PathVariable Long id) {
        return WebResponse.<Siswa>builder()
                .status("00")
                .pesan("Berhasil mengambil data siswa")
                .data(siswaService.getSiswaById(id))
                .build();
    }

    // Read By NIS
    @GetMapping("/nis/{nis}")
    public WebResponse<Siswa> getSiswaByNis(@PathVariable String nis) {
        return WebResponse.<Siswa>builder()
                .status("00")
                .pesan("Berhasil mengambil data siswa")
                .data(siswaService.getSiswaByNis(nis))
                .build();
    }

    // Update
    @PutMapping("/{id}")
    public WebResponse<Siswa> updateSiswa(@PathVariable Long id, @Valid @RequestBody Siswa siswa) {
        return WebResponse.<Siswa>builder()
                .status("00")
                .pesan("Siswa berhasil diupdate")
                .data(siswaService.updateSiswa(id, siswa))
                .build();
    }

    // Delete
    @DeleteMapping("/{id}")
    public WebResponse<String> deleteSiswa(@PathVariable Long id) {
        siswaService.deleteSiswa(id);
        return WebResponse.<String>builder()
                .status("00")
                .pesan("Data berhasil dihapus")
                .data(null)
                .build();
    }

}