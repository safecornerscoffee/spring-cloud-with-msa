package com.safecornerscoffee.msa.product.service;

import com.safecornerscoffee.msa.product.entity.Product;
import com.safecornerscoffee.msa.product.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Iterable<Product> getProducts() {
        return productRepository.findAll();
    }
}
