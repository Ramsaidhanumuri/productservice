package dev.ramsai.productservice.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.ramsai.productservice.dtos.GenericProductDto;
import dev.ramsai.productservice.dtos.GetProductTitleRequestDto;
import dev.ramsai.productservice.dtos.ProductDto;
import dev.ramsai.productservice.services.CategoryService;

@RestController
@RequestMapping("${api.version}/catogeries")
public class CatogeryController {

	private CategoryService categoryService;

	public CatogeryController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	@GetMapping()
	public void getAllCatogeries() {
		
	}
	
	@GetMapping("/{uuid}")
	public List<ProductDto> getCategoryById(@PathVariable("uuid") String uuid) {
		return categoryService.getCategory(uuid);
	}
	
	@GetMapping("/titles/")
	public List<String> getProductTitles(@RequestBody GetProductTitleRequestDto requestDto ){
		return categoryService.getProductTitles(requestDto);
	}
}
