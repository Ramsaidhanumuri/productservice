package dev.ramsai.productservice.dtos;

import java.util.UUID;

import dev.ramsai.productservice.models.Price;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDto {
	private String uuid;
	
	private String title;

    private String description;
    
    private String category;

    private String image;
    
    private Price price;
}
