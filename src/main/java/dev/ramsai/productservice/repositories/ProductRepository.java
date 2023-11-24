package dev.ramsai.productservice.repositories;

import java.util.UUID;
import java.util.function.Function;

import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.stereotype.Repository;

import dev.ramsai.productservice.models.Product;
import java.util.List;
import java.util.Optional;

import dev.ramsai.productservice.models.Category;
import dev.ramsai.productservice.models.Price;


@Repository
public interface ProductRepository extends JpaRepository<Product, UUID>{

	List<Product> findByTitleEquals(String title);
	
	List<Product> findByTitleEqualsAndPrice_Price(String title, double price);
	
	Optional<Product> findById(UUID id);
	
	List<Product> findAllByCategoryIn(List<Category> categories);
}
