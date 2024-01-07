package com.litmus.product.management.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.litmus.product.management.entity.Product;
import com.litmus.product.management.service.ProductService;

@RunWith(MockitoJUnitRunner.class)
public class ProductControllerTest {

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
    }

    @Test
    public void testGetAllProducts() throws Exception {
    	    	
    	List<Product> productList = getProductList();//new ArrayList<Product>();
    	
        when(productService.getAllProducts()).thenReturn(productList);

        mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].productId", is(1)))
                .andExpect(jsonPath("$[0].name", is("Product1")))
                .andExpect(jsonPath("$[1].productId", is(2)))
                .andExpect(jsonPath("$[1].name", is("Product2")));

        verify(productService, times(1)).getAllProducts();
    }

  	@Test
    public void testGetProductById() throws Exception {
        long productId = 1L;
        Product product = getProduct();

        when(productService.getProductById(productId)).thenReturn(product);

        mockMvc.perform(get("/products/{productId}", productId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId", is(12L)))
                .andExpect(jsonPath("$.price", is(114.0)));

        verify(productService, times(1)).getProductById(productId);
    }

    private Product getProduct() {
    	Product mockProduct = new Product();
    	mockProduct.setProductId(12L);
    	mockProduct.setTitle("WD 4TB Gaming Drive Works with Playstation 4 Portable External Hard Drive");
    	mockProduct.setPrice(114.0);
    	mockProduct.setDescription("Expand your PS4 gaming experience, Play anywhere Fast and easy, setup Sleek design with high capacity, 3-year manufacturer's limited warranty");
    	mockProduct.setCategory("electronics");
    	mockProduct.setImage("https://fakestoreapi.com/img/61mtL65D4cL._AC_SX679_.jpg");
    	return mockProduct;
	}

	private List<Product> getProductList() {
    	List<Product> products = new ArrayList<Product>();
    	Product mockProduct = new Product();
    	mockProduct.setProductId(12L);
    	mockProduct.setTitle("WD 4TB Gaming Drive Works with Playstation 4 Portable External Hard Drive");
    	mockProduct.setPrice(114.0);
    	mockProduct.setDescription("Expand your PS4 gaming experience, Play anywhere Fast and easy, setup Sleek design with high capacity, 3-year manufacturer's limited warranty");
    	mockProduct.setCategory("electronics");
    	mockProduct.setImage("https://fakestoreapi.com/img/61mtL65D4cL._AC_SX679_.jpg");

    	products.add(mockProduct);
		return products;
	}



}

