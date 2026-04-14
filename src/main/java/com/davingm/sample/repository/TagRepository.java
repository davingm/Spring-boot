package com.davingm.sample.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.davingm.sample.model.Tag;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {
    java.util.Optional<Tag> findByName(String name);
}
