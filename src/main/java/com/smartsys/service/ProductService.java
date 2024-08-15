package com.smartsys.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.smartsys.entities.Product;

@Service
public interface ProductService {

    public List<Product> getAllProducts();
}
