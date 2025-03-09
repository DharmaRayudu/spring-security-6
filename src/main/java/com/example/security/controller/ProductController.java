package com.example.security.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ProductController {
	
	private record Product(Integer productId, String productName, double price) {}

	List<Product>  products = new ArrayList<>(
			List.of(new Product(1, "IPhone", 120000.00),
					new Product(2, "OnePlus6T", 80000.00),
					new Product(3, "Samsung", 75000.00))
			);


	@GetMapping("/products")
	public List<Product> getProducts(){
		return products;
	}

	@PostMapping("/products")
	public Product saveProducts(@RequestBody Product product){
		products.add(product);
		return product;
	}
}
