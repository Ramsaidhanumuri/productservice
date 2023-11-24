package dev.ramsai.productservice.services;

import java.util.List;
import dev.ramsai.productservice.dtos.GetProductTitleRequestDto;
import dev.ramsai.productservice.dtos.ProductDto;

public interface CategoryService {

	List<ProductDto> getCategory(String uuid);

	List<String> getProductTitles(GetProductTitleRequestDto uuids);
}
