package com.litmus.product.management.customexception;

//ProductNotFoundException.java
public class ProductNotFoundException extends CustomException {

	private static final long serialVersionUID = 1L;

	public ProductNotFoundException(String message) {
		super(message);
	}
}
