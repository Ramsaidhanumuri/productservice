package dev.ramsai.productservice.dtos;

import java.util.UUID;

import dev.ramsai.productservice.models.Category;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenericProductDto {
	private String id;
	
	private String title;
	
	private double price;
	
	private String category;
	
	private String description;
	
	private String image;
}
