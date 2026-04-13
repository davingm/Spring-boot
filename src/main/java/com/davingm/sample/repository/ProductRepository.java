package com.davingm.sample.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.davingm.sample.model.Product;

@Repository // mendefinisikan ini repo
public interface ProductRepository extends JpaRepository<Product, Long> // long di gunakan karena tipe data id adalah long  
{
    

}
