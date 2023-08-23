package org.example.service;

import org.example.model.Product;

public interface ProductService
{

	Product getByName(String name);

	void refreshAllProducts();

	Product updateProduct(Product product);
}
