package com.project.ecommerce.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query("SELECT p FROM Product p WHERE p.id IN :productIds")
    List<Product> findAllByIdInOrderById(List<Integer> productIds);

}
