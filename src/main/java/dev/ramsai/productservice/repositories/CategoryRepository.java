package dev.ramsai.productservice.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.ramsai.productservice.models.Category;

public interface CategoryRepository extends JpaRepository<Category, UUID>{

	List<Category> findAllById(Iterable<UUID> uuids);
}
