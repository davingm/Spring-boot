package com.davingm.sample.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.davingm.sample.model.Product;
import java.util.List;


@Repository // mendefinisikan ini repo
public interface ProductRepository extends JpaRepository<Product, Long> // long di gunakan karena tipe data id adalah long  
{   
    List<Product> findByCategory_IdAndHargaGreaterThanEqual(Long categoryId, Double harga);

    List<Product> findByCategory_NameIgnoreCaseAndHargaGreaterThanEqual(String categoryName, Double harga);

    @Query("""
        SELECT DISTINCT p FROM Product p
        JOIN p.tags t
        WHERE t.id = :tagId AND p.category.id = :categoryId
    """)
    List<Product> findByTagIdAndCategoryId(
        @Param("tagId") Long tagId,
        @Param("categoryId") Long categoryId
    );

    @Query("""
        SELECT DISTINCT p FROM Product p
        JOIN p.tags t
        WHERE t.id = :tagId AND LOWER(p.category.name) = LOWER(:categoryName)
    """)
    List<Product> findByTagIdAndCategoryName(
        @Param("tagId") Long tagId,
        @Param("categoryName") String categoryName
    );

    List<Product> findByHargaBetween(Double minPrice, Double maxPrice);

}
