package com.codeninja.productservice.service;

import com.codeninja.productservice.model.ProductRequest;
import com.codeninja.productservice.model.ProductResponse;

public interface ProductService {

	long addProduct(ProductRequest productRequest);

	ProductResponse getProductById(long id);

	void reduceQuantity(long productId, long quantity);
	
	

}
