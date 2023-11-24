package dev.ramsai.productservice.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import dev.ramsai.productservice.dtos.GenericProductDto;
import dev.ramsai.productservice.services.ProductService;

@WebMvcTest(ProductController.class)
class ProductControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ProductService productService;

	@Test
	void testGetAllProductsReturnEmptyList() throws Exception {

		Mockito.when(productService.getAllProducts()).thenReturn(Collections.emptyList());

		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.get("/api/v1/products").contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isNotFound()).andReturn();

		Exception resolvedException = result.getResolvedException();
		assertEquals("No data available!!! Please try again after some time.", resolvedException.getMessage());
	}

	@Test
	void testGetAllProductsReturnList() throws Exception {

		ArrayList<GenericProductDto> products = new ArrayList<>();
		products.add(new GenericProductDto());
		products.add(new GenericProductDto());
		products.add(new GenericProductDto());

		Mockito.when(productService.getAllProducts()).thenReturn(products);

		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/products").contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	void testGetProductByIdNotExits() throws Exception {

		when(productService.getProductById("0")).thenReturn(null);

		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.get("/api/v1/products/0").contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isNotFound()).andReturn();

		Exception resolvedException = result.getResolvedException();
		assertEquals("Product Id: 0 doesn't exist.", resolvedException.getMessage());

	}

	@Test
	void testGetProductById() throws Exception {

		GenericProductDto product = new GenericProductDto();
		product.setId("1");
		product.setTitle("iPhone 15 Pro Max");
		product.setImage("some image");
		product.setCategory("mobile phones");
		product.setDescription("Best iPhone Ever");

		when(productService.getProductById("1")).thenReturn(product);

		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/products/1").contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());

	}
}
