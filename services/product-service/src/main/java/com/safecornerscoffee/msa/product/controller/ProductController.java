package com.safecornerscoffee.msa.product.controller;

import com.safecornerscoffee.msa.product.vo.ResponseProduct;
import com.safecornerscoffee.msa.product.entity.Product;
import com.safecornerscoffee.msa.product.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<ResponseProduct>> getProducts() {

        Iterable<Product> products = productService.getProducts();

        List<ResponseProduct> result = new ArrayList<>();
        products.forEach(v -> {
            result.add(new ModelMapper().map(v, ResponseProduct.class));
        });
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
