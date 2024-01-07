package com.litmus.product.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.litmus.product.management.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
