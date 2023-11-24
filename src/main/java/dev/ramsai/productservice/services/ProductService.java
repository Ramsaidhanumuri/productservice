package dev.ramsai.productservice.services;

import java.util.List;

import dev.ramsai.productservice.dtos.GenericProductDto;
import dev.ramsai.productservice.exceptions.NoDataFoundException;

public interface ProductService {

	List<GenericProductDto> getAllProducts();

	GenericProductDto getProductById(String id);

	GenericProductDto createProduct(GenericProductDto product);

	GenericProductDto deleteProductById(Long id);
}
