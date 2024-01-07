package com.litmus.product.management.entity;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

@Entity
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull(message = "Product ID is mandatory")
    private Long productId;

    @NotBlank(message = "Name is mandatory")
    @Pattern(regexp = "^[a-zA-Z0-9\\s]+$", message = "Name should not contain special characters")
    private String name;

    @NotBlank(message = "Description is mandatory")
    @Size(max = 100, message = "Description should not exceed 100 characters")
    private String description;

    @NotNull(message = "Price is mandatory")
    private Double price;
    
    
    private String category;
    private String manufacturer;
 
    private String title;
    private String image;
	/*
	 * @JsonProperty("rating") private Rating rating;
	 */

   
}