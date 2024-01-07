package com.litmus.product.management.feign;

import java.util.List;

//ProductFeignClient.java
//import org.springframework.cloud.openfeign.*
import org.springframework.cloud.openfeign.*;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.litmus.product.management.entity.Product;

//https://fakestoreapi.com/products/12
@FeignClient(name = "productFeignClient" , url="https://fakestoreapi.com/")
public interface ProductFeignClient {

 @GetMapping("/products")
 List<Product> getAllProducts();

 @GetMapping("/products/{productId}")
 Product getProductById(@PathVariable Long productId);

 @PostMapping("/products")
 Product createProduct(@RequestBody Product product);

 @PutMapping("/products/{productId}")
 Product updateProduct(@PathVariable Long productId, @RequestBody Product product);

 @DeleteMapping("/products/{productId}")
 void deleteProduct(@PathVariable Long productId);
}

