package dev.ramsai.productservice.controllers;

import java.util.Collections;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import dev.ramsai.productservice.dtos.GenericProductDto;
import dev.ramsai.productservice.exceptions.EmptyInputException;
import dev.ramsai.productservice.exceptions.NoDataFoundException;
import dev.ramsai.productservice.services.ProductService;

@RestController
@RequestMapping("${api.version}/products")
public class ProductController {

	private ProductService productService;

	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@GetMapping()
	public List<GenericProductDto> getAllProducts() throws NoDataFoundException {
		List<GenericProductDto> result = productService.getAllProducts();

		if (result.isEmpty()) {
			throw new NoDataFoundException("No data available!!! Please try again after some time.");
		}

		return result;
	}

	@GetMapping("/{id}")
	public GenericProductDto getProductById(@PathVariable("id") String id) throws NoDataFoundException {

		GenericProductDto product = productService.getProductById(id);

		if (product==null) {
			throw new NoDataFoundException("Product Id: " + id + " doesn't exist.");
		}

		return product;
	}

	@PostMapping()
	public GenericProductDto createProduct(@RequestBody GenericProductDto product) throws EmptyInputException {
		if(product.getTitle()==null || product.getPrice()==0 || product.getCategory()==null ||
				product.getImage()==null || product.getDescription()==null) {
			throw new EmptyInputException("Product details cannot be empty.");
		}
		return productService.createProduct(product);
	}

	@PutMapping("/{id}")
	public void updateProductById(@PathVariable("id") String id, @RequestBody GenericProductDto product) throws EmptyInputException {
		if(product.getTitle()==null || product.getPrice()==0 || product.getCategory()==null ||
				product.getImage()==null || product.getDescription()==null) {
			throw new EmptyInputException("Product details cannot be empty.");
		}
		
		GenericProductDto product = productService.updateProductById(id, product);

		if (product==null) {
			throw new NoDataFoundException("Product Id: " + id + " doesn't exist.");
		}

		return product;
		
	}

	@DeleteMapping("/{id}")
	public GenericProductDto deleteProductById(@PathVariable("id") Long id) {
		return productService.deleteProductById(id);
	}
}
