package dev.ramsai.productservice.services;

import java.util.List;

import dev.ramsai.productservice.dtos.GenericProductDto;
import dev.ramsai.productservice.exceptions.BadGatewayException;
import dev.ramsai.productservice.exceptions.NoDataFoundException;

public interface ProductService {

	List<GenericProductDto> getAllProducts() throws BadGatewayException;

	GenericProductDto getProductById(String id) throws NoDataFoundException;

	GenericProductDto createProduct(GenericProductDto product);
	
	GenericProductDto updateProductById(String id, GenericProductDto product);

	GenericProductDto deleteProductById(Long id);
}
