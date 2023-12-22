package com.codeninja.productservice.service;

import static org.springframework.beans.BeanUtils.copyProperties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codeninja.productservice.entity.Product;
import com.codeninja.productservice.exception.ProductServiceCustomException;
import com.codeninja.productservice.model.ProductRequest;
import com.codeninja.productservice.model.ProductResponse;
import com.codeninja.productservice.repository.ProductRepository;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductRepository productRepository;

	@Override
	public long addProduct(ProductRequest productRequest) {
		log.info("Adding Product...");

		Product product = Product.builder().productName(productRequest.getName()).price(productRequest.getPrice())
				.quantity(productRequest.getQuantity()).build();

		productRepository.save(product);
		log.info("Product Created...");
		return product.getProductId();
	}

	@Override
	public ProductResponse getProductById(long id) {
		log.info("Get the product for product id : {}", id);
		Product product = productRepository.findById(id).orElseThrow(
				() -> new ProductServiceCustomException("Product with given id not found", "PRODUCT_NOT_FOUND"));
		ProductResponse productResponse = new ProductResponse();
		copyProperties(product, productResponse);
		return productResponse;
	}

	@Override
	public void reduceQuantity(long productId, long quantity) {
		log.info("Reduce quantity {} for Id : {} ", quantity, productId);
		Product product = productRepository.findById(productId).orElseThrow(
				() -> new ProductServiceCustomException("Product with given id not found", "PRODUCT_NOT_FOUND"));
		if (product.getQuantity() < quantity) {
			throw new ProductServiceCustomException("Product does not have sufficent quantity", "INSUFFICENT_QUANTITY");
		}

		product.setQuantity(product.getQuantity() - quantity);
		productRepository.save(product);
		log.info("Product quantity updated successfully...");
	}

}
