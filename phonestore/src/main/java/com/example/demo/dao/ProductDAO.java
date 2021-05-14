package com.example.demo.dao;

import com.example.demo.entity.Account;
import com.example.demo.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDAO extends JpaRepository<Product, Long> {
    Product findProductById(Long id);
}
