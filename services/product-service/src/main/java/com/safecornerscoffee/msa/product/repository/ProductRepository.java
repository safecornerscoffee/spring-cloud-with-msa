package com.safecornerscoffee.msa.product.repository;

import com.safecornerscoffee.msa.product.entity.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
    Optional<Product> findByProductId(String productId);
}
