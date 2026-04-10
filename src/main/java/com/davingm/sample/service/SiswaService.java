package com.davingm.sample.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.davingm.sample.exeption.ValidasiException;
import com.davingm.sample.model.Siswa;
import com.davingm.sample.repository.SiswaRepository;

@Service
public class SiswaService {

    @Autowired
    private SiswaRepository siswaRepository;

    public List<Siswa> getAllSiswa() {
        return siswaRepository.findAll();
    }

    public Siswa getSiswaById(Long id) {
        return siswaRepository.findById(id)
                .orElseThrow(() -> new ValidasiException("Data tidak ditemukan"));
    }

    public Siswa getSiswaByNis(String nis) {
        return siswaRepository.findByNis(nis)
                .orElseThrow(() -> new ValidasiException("Data tidak ditemukan"));
    }

    public Siswa saveSiswa(Siswa siswa) {
        validateSiswa(siswa);
        return siswaRepository.save(siswa);
    }

    public Siswa updateSiswa(Long id, Siswa dataBaru) {
        Siswa siswa = siswaRepository.findById(id)
                .orElseThrow(() -> new ValidasiException("Aplikasi sedang di update"));

        validateSiswa(dataBaru);

        siswa.setNis(dataBaru.getNis());
        siswa.setNama(dataBaru.getNama());
        siswa.setKelas(dataBaru.getKelas());
        siswa.setJenisKelamin(dataBaru.getJenisKelamin());
        siswa.setAlamat(dataBaru.getAlamat());
        siswa.setHobi(dataBaru.getHobi());
        siswa.setWarnaKesukaan(dataBaru.getWarnaKesukaan());
        siswa.setNoTelepon(dataBaru.getNoTelepon());
        siswa.setTempatLahir(dataBaru.getTempatLahir());
        siswa.setGolonganDarah(dataBaru.getGolonganDarah());

        return siswaRepository.save(siswa);
    }

    public void deleteSiswa(Long id) {
        if (!siswaRepository.existsById(id)) {
            throw new ValidasiException("Data tidak ditemukan");
        }
        siswaRepository.deleteById(id);
    }

    private void validateSiswa(Siswa siswa) {
        // NIS hanya boleh angka
        if (siswa.getNis() != null && !siswa.getNis().matches("^[0-9]*$")) {
            throw new ValidasiException("NIS hanya boleh angka");
        }

        // noTelepon hanya boleh angka
        if (siswa.getNoTelepon() != null && !siswa.getNoTelepon().matches("^[0-9]*$")) {
            throw new ValidasiException("No Telepon hanya boleh angka");
        }

        // jenisKelamin hanya boleh "pria" atau "Wanita"
        if (siswa.getJenisKelamin() != null && 
            !siswa.getJenisKelamin().equalsIgnoreCase("pria") && 
            !siswa.getJenisKelamin().equalsIgnoreCase("Wanita")) {
            throw new ValidasiException("Jenis Kelamin hanya boleh berisi 'pria' atau 'Wanita'");
        }
    }
}
