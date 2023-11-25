package dev.ramsai.productservice.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

import com.fasterxml.jackson.databind.ObjectMapper;

import dev.ramsai.productservice.dtos.GenericProductDto;
import dev.ramsai.productservice.services.ProductService;

@WebMvcTest(ProductController.class)
class ProductControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ProductService productService;
	
	@Autowired
	private ObjectMapper objectMapper;

	@Test
	void testGetAllProductsReturnEmptyList() throws Exception {

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

	@Test
	void testCreateProductWithEmptyFields() throws Exception {

		MvcResult result = mockMvc.perform(
				MockMvcRequestBuilders.post("/api/v1/products").contentType(MediaType.APPLICATION_JSON).content("{}"))
				.andExpect(MockMvcResultMatchers.status().isBadRequest()).andReturn();

		Exception resolvedException = result.getResolvedException();
		assertEquals("Product details cannot be empty.", resolvedException.getMessage());

	}

	@Test
	void testCreateProductWithData() throws Exception {
		
		GenericProductDto expectedProduct = new GenericProductDto();
		expectedProduct.setId("1");
		expectedProduct.setTitle("iPhone 15 Pro Max");
		expectedProduct.setImage("some image");
		expectedProduct.setCategory("mobile phones");
		expectedProduct.setDescription("Best iPhone Ever");
		expectedProduct.setPrice(109.8);
		
		mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/products")
				.contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(expectedProduct)))
		.andExpect(MockMvcResultMatchers.status().isOk());
	}
}
