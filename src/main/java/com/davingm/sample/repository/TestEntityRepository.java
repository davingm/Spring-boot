package com.davingm.sample.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.davingm.sample.model.TestEntity;

public interface TestEntityRepository extends JpaRepository<TestEntity, Long> {
}
