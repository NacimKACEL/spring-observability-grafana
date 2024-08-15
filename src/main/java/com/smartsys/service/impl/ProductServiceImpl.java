package com.smartsys.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.smartsys.entities.Product;
import com.smartsys.repository.ProductRepository;
import com.smartsys.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getAllProducts() {
        return this.productRepository.findAll();
    }

}
