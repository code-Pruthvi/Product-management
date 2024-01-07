package com.litmus.product.management.service;

import java.util.List;

import com.litmus.product.management.entity.Product;

public interface ProductService {

	List<Product> getAllProducts();

	Product getProductById(Long productId);

	Product createProduct(Product product);

	Product updateProduct(Long productId, Product product);

	void deleteProduct(Long productId);

}
