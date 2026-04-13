package com.davingm.sample.service;

import java.util.List;

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

 

    // execption jika nisn sudah ada
    public Siswa saveSiswa(Siswa siswa) {
        if (siswaRepository.findByNis(siswa.getNis()).isPresent()) {
            throw new ValidasiException("NIS sudah ada");
        }
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

        // ^ : Menandakan awal dari string.
        // $ : Menandakan akhir dari string.
        // [0-9] : Menandakan bahwa karakter yang diizinkan adalah angka dari 0 sampai 9.
        // * : Menandakan bahwa karakter sebelumnya boleh muncul 0 kali atau lebih.
        // + : Menandakan bahwa karakter sebelumnya boleh muncul 1 kali atau lebih.
        // ? : Menandakan bahwa karakter sebelumnya boleh muncul 0 kali atau 1 kali.
        // | : Menandakan bahwa karakter sebelumnya boleh muncul 0 kali atau lebih.
        // ( ) : Menandakan bahwa karakter sebelumnya boleh muncul 0 kali atau lebih.
        // [ ] : Menandakan bahwa karakter sebelumnya boleh muncul 0 kali atau lebih.
        // { } : Menandakan bahwa karakter sebelumnya boleh muncul 0 kali atau lebih.
        


        // NIS hanya boleh angka
        if (siswa.getNis() != null && !siswa.getNis().matches("^[0-9]*$")) {
            throw new ValidasiException("NIS hanya boleh angka");
        }

        // noTelepon hanya boleh angka
        if (siswa.getNoTelepon() != null && !siswa.getNoTelepon().matches("^[0-9]*$")) {
            throw new ValidasiException("No Telepon hanya boleh angka");
        }

        // jenisKelamin hanya boleh dua
        if (siswa.getJenisKelamin() != null && !siswa.getJenisKelamin().matches("^(pria|wanita)$")) {
            throw new ValidasiException("Jenis Kelamin hanya boleh pria atau wanita");
        }

        // golongan darah validation
        if (siswa.getGolonganDarah() != null && !siswa.getGolonganDarah().matches("^(A|B|AB|O)$")) {
            throw new ValidasiException("Golongan Darah hanya boleh A, B, AB, atau O");
        }
    }
}
