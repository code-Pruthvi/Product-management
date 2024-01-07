package com.litmus.product.management.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.litmus.product.management.entity.Product;
import com.litmus.product.management.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductService productService;

	@GetMapping
	public List<Product> getAllProducts() {
		return productService.getAllProducts();
	}

	@GetMapping("/{productId}")
	public ResponseEntity<Object> getProductById(@PathVariable Long productId) {
		Product product = productService.getProductById(productId);
		if (product == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(product);
	}

	@PostMapping("/create")
	public ResponseEntity<Object> createProduct(@Valid @RequestBody Product product, BindingResult result) {
		if (result.hasErrors()) {
			return ResponseEntity.badRequest().body(result.getAllErrors());
		}

		// Perform business logic to save the product
		Product createdProduct = productService.createProduct(product);

		return ResponseEntity.ok(createdProduct);
	}

	@PutMapping("/{productId}")
	public ResponseEntity<Object> updateProduct(@PathVariable Long productId, @Valid @RequestBody Product product) {
		Product updatedProduct = productService.updateProduct(productId, product);

		if (updatedProduct == null) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(updatedProduct);
	}

	@DeleteMapping("/{productId}")
	public ResponseEntity<Object> deleteProduct(@PathVariable Long productId) {
		productService.deleteProduct(productId);
		return ResponseEntity.noContent().build();
	}
}