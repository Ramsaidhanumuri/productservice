package dev.ramsai.productservice;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import dev.ramsai.productservice.models.Category;
import dev.ramsai.productservice.models.Price;
import dev.ramsai.productservice.models.Product;
import dev.ramsai.productservice.repositories.CategoryRepository;
import dev.ramsai.productservice.repositories.PriceRepository;
import dev.ramsai.productservice.repositories.ProductRepository;
import dev.ramsai.productservice.services.ProductService;
import jakarta.transaction.Transactional;

@SpringBootApplication
public class ProductserviceApplication //implements CommandLineRunner
{
	
//	@Autowired
//	private ProductRepository productRepository;
//	
//	@Autowired
//	private CategoryRepository categoryRepository;
//
//	@Autowired
//	private PriceRepository priceRepository;

	public static void main(String[] args) {
		SpringApplication.run(ProductserviceApplication.class, args);
	}

//	@Override
//	public void run(String... args) throws Exception {
//		// TODO Auto-generated method stub
//		
//		Category category = new Category();
//		category.setName("Apple Devices");
//		Category saveCategory =  categoryRepository.save(category);
//		
//		Product product = new Product();
//		product.setTitle("iPhone 15 Pro");
//		product.setDescription("The best phone");
//		product.setCategory(saveCategory);
//		
//		productRepository.save(product);
//		
//		Optional<Category> category2 = categoryRepository.findById(UUID.fromString("b7cbe27d-5be2-489c-aa85-594d96265684"));
//		
//		System.out.println(category2);
//		
//		Price price = new Price();
//		price.setPrice(12);
//		priceRepository.save(price);
//		
//		List<Product> findByTitleEquals = productRepository.findByTitleEquals("iPhone 15 Pro");
//		
//		findByTitleEquals.forEach(products -> System.out.println(products.getTitle()));
//		
//	}
}
