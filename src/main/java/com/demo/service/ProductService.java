package com.demo.service;

import com.demo.model.Category;
import com.demo.model.Product;
import com.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

//TODO: Connect to database
@Service
public class ProductService {
    @Autowired
    ProductRepository repo;
    public List<Product> getAll(){
        return repo.findAll();
    }

    public Product findById(int id) {
        return repo.findById(id).orElse(null);
    }
}
