package dev.ramsai.productservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.ramsai.productservice.models.Price;

public interface PriceRepository extends JpaRepository<Price, Long>{

}
