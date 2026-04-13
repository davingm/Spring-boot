package com.davingm.sample.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

import com.davingm.sample.model.Siswa;

@Repository
public interface SiswaRepository extends JpaRepository<Siswa, Long> {
    boolean existsByNis(String nis);
    Optional<Siswa> findByNis(String nis);
}