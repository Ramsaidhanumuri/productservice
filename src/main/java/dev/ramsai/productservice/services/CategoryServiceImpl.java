package dev.ramsai.productservice.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import dev.ramsai.productservice.dtos.GenericProductDto;
import dev.ramsai.productservice.dtos.GetProductTitleRequestDto;
import dev.ramsai.productservice.dtos.ProductDto;
import dev.ramsai.productservice.models.Category;
import dev.ramsai.productservice.models.Product;
import dev.ramsai.productservice.repositories.CategoryRepository;
import dev.ramsai.productservice.repositories.ProductRepository;

@Service
public class CategoryServiceImpl implements CategoryService {

	private CategoryRepository categoryRepository;
	private ProductRepository productRepository;

	public CategoryServiceImpl(CategoryRepository categoryRepository,
			ProductRepository productRepository) {
		this.categoryRepository = categoryRepository;
		this.productRepository = productRepository;
	}
	
	private ProductDto convertProductToGenericProductDto(Product product) {

		ProductDto genericProductDto = new ProductDto();
		genericProductDto.setUuid(product.getUuid().toString());
		genericProductDto.setTitle(product.getTitle());
		genericProductDto.setCategory(product.getCategory().getName());
		genericProductDto.setDescription(product.getDescription());
		genericProductDto.setImage(product.getImage());
		genericProductDto.setPrice(product.getPrice());

		return genericProductDto;
	}

	@Override
	public List<ProductDto> getCategory(String uuid) {
		Optional<Category> categoryOptional = categoryRepository.findById(UUID.fromString(uuid));
		
		if(categoryOptional.isEmpty()) {
			return null;
		}
		
		Category category = categoryOptional.get();
		List<Product> products = category.getProducts();
		List<ProductDto> productDtos = new ArrayList<ProductDto>();
		
		for(Product product: products) {
			productDtos.add(convertProductToGenericProductDto(product));
		}
		
		return productDtos;
	}

	@Override
	public List<String> getProductTitles(GetProductTitleRequestDto requestDto) {
		List<String> strUuids = requestDto.getUuids();
		
		List<UUID> uuids = strUuids.stream()
				.map(uuid -> UUID.fromString(uuid)).toList();
		
		List<Category> categories = categoryRepository.findAllById(uuids);
		List<Product> products = productRepository.findAllByCategoryIn(categories);
		
		List<String> titles = products.stream()
				.map(product -> product.getTitle())
				.toList();
		
		return titles;
	}

}
