package com.demo.repository;

import com.demo.model.Product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {

    @Query("SELECT p FROM Product p WHERE p.category.id=?1 AND p.name LIKE ?2 and p.price BETWEEN ?3 AND ?4")
    Page<Product> searchByCategoryNamePrice(String cid, String kw, int minPrice, int maxPrice, Pageable pageable);

    @Query("SELECT p FROM Product p WHERE p.name LIKE ?1 and p.price BETWEEN ?2 AND ?3")
    Page<Product> searchByNamePrice(String kw, int minPrice, int maxPrice, Pageable pageable);
}
