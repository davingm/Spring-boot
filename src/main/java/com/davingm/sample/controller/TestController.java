package com.davingm.sample.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.davingm.sample.model.TestEntity;
import com.davingm.sample.repository.TestEntityRepository;

@RestController
public class TestController {

    private final TestEntityRepository repository;

    public TestController(TestEntityRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/test-insert")
    public String insertTest() {
        TestEntity entity = new TestEntity();
        entity.setName("Data Test " + System.currentTimeMillis());
        repository.save(entity);
        return "Berhasil menyimpan data ke test_table di PostgreSQL!";
    }
}
