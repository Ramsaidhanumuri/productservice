package dev.ramsai.productservice.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import dev.ramsai.productservice.dtos.GenericProductDto;
import dev.ramsai.productservice.models.Category;
import dev.ramsai.productservice.models.Price;
import dev.ramsai.productservice.models.Product;
import dev.ramsai.productservice.repositories.CategoryRepository;
import dev.ramsai.productservice.repositories.PriceRepository;
import dev.ramsai.productservice.repositories.ProductRepository;

@Service("selfProductService")
public class SelfProductService implements ProductService {

	private ProductRepository productRepository;
	private CategoryRepository categoryRepository;
	private PriceRepository priceRepository;

	public SelfProductService(ProductRepository productRepository, CategoryRepository categoryRepository,
			PriceRepository priceRepository) {
		this.productRepository = productRepository;
		this.categoryRepository = categoryRepository;
		this.priceRepository = priceRepository;
	}

	private GenericProductDto convertProductToGenericProductDto(Product product) {

		GenericProductDto genericProductDto = new GenericProductDto();
		genericProductDto.setId(product.getUuid().toString());
		genericProductDto.setTitle(product.getTitle());
		genericProductDto.setCategory(product.getCategory().getName());
		genericProductDto.setDescription(product.getDescription());
		genericProductDto.setImage(product.getImage());
		genericProductDto.setPrice(product.getPrice().getPrice());

		return genericProductDto;

	}

	public GenericProductDto getProductById(String id) {
		UUID uuid = UUID.fromString(id);
		Optional<Product> optionalProduct = productRepository.findById(uuid);
		Product product = optionalProduct.orElse(null);

		return convertProductToGenericProductDto(product);
	}

	@Override
	public GenericProductDto createProduct(GenericProductDto product) {
		// TODO Auto-generated method stub

		Price price = new Price();
		price.setPrice(product.getPrice());
		Price savePrice = priceRepository.save(price);

		Category category = new Category();
		category.setName(product.getCategory());
		Category saveCategory = categoryRepository.save(category);

		Product productObj = new Product();
		productObj.setCategory(saveCategory);
		productObj.setDescription(product.getDescription());
		productObj.setImage(product.getImage());
		productObj.setPrice(savePrice);
		productObj.setTitle(product.getTitle());
		Product saveProduct = productRepository.save(productObj);

		return convertProductToGenericProductDto(saveProduct);
	}

	@Override
	public List<GenericProductDto> getAllProducts() {
		// TODO Auto-generated method stub

		List<GenericProductDto> productDtos = new ArrayList<GenericProductDto>();
		List<Product> products = productRepository.findAll();

		for (Product product : products) {
			productDtos.add(convertProductToGenericProductDto(product));
		}

		return productDtos;
	}

	@Override
	public GenericProductDto deleteProductById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
}
