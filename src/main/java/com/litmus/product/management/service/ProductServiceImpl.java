package com.litmus.product.management.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.litmus.product.management.customexception.ProductNotFoundException;
import com.litmus.product.management.customexception.ProductUpdateException;
import com.litmus.product.management.entity.Product;
import com.litmus.product.management.feign.ProductFeignClient;
import com.litmus.product.management.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	ProductFeignClient productFeignClient;

	@Override
	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}

	// getProductById : to fetch product details by Id from repository
	@Override
	public Product getProductById(Long productId) {
		
		Optional<Product> optionalProduct = productRepository.findById(productId);
		
		if (optionalProduct.isPresent()) {
			Product product = optionalProduct.get();
			Product updateProductFO = new Product();
			if (product.getTitle() != null && !product.getTitle().isEmpty()) {

				return product;
			} else {
				updateProductFO = fetchProductDetailsOF(productId);
				// Before return Product, update the table with fetched product values
				updateProduct(productId, updateProductFO);
				return updateProductFO;
			}
		} else {
			throw new ProductNotFoundException("Product not found for ID: " + productId);
		}
		
	}

	@Override
	public Product createProduct(Product product) {
		return productRepository.save(product);
	}

	// Check if productId is there in repo;
	// if yes fetch details from fakerest and update the product
	
	@Override
	public Product updateProduct(Long productId, Product product) {
	
		// if product id already exist in table, it will update it
		if (productRepository.existsById(productId)) {
		
			product.setProductId(productId);
			return productRepository.save(product);
	
		} else {
		
			// if id is not found, then it will check from FakeRest API call & then
			Product updatedProduct = fetchProductDetailsOF(productId);
			if (null != updatedProduct) {
				return productRepository.save(product);
			} else {
				throw new ProductUpdateException("Failed to update product for ID: " + productId);
			}
		}

	}

	@Override
	public void deleteProduct(Long productId) {
		productRepository.deleteById(productId);
	}

	public Product fetchProductDetailsOF(Long productId) {
		return productFeignClient.getProductById(productId);
	}
}