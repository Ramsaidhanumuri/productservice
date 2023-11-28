package dev.ramsai.productservice.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Primary;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import dev.ramsai.productservice.dtos.GenericProductDto;
import dev.ramsai.productservice.exceptions.BadGatewayException;
import dev.ramsai.productservice.exceptions.NoDataFoundException;
import dev.ramsai.productservice.thirdpartyclients.productservice.fakestore.FakeStoreProductDto;
import dev.ramsai.productservice.thirdpartyclients.productservice.fakestore.FakeStoreProductServiceClient;

@Primary
@Service("fakeStoreProductService")
public class FakeStoreProductService implements ProductService {

	private FakeStoreProductServiceClient fakeStoreProductServiceClient;

	public FakeStoreProductService(FakeStoreProductServiceClient fakeStoreProductServiceClient) {
		this.fakeStoreProductServiceClient = fakeStoreProductServiceClient;
	}

	private GenericProductDto convertFakeStoreProductIntoGenericProduct(FakeStoreProductDto fakeStoreProductDto) {

		GenericProductDto product = new GenericProductDto();

		product.setId(fakeStoreProductDto.getId().toString());
		product.setTitle(fakeStoreProductDto.getTitle());
		product.setPrice(fakeStoreProductDto.getPrice());
		product.setDescription(fakeStoreProductDto.getDescription());
		product.setImage(fakeStoreProductDto.getImage());
		product.setCategory(fakeStoreProductDto.getCategory());

		return product;
	}

	@Override
	public List<GenericProductDto> getAllProducts() throws BadGatewayException {

		List<GenericProductDto> genericProductDtos = new ArrayList<>();

		for (FakeStoreProductDto fakeStoreProductDto : fakeStoreProductServiceClient.getAllProducts()) {
			genericProductDtos.add(convertFakeStoreProductIntoGenericProduct(fakeStoreProductDto));
		}

		return genericProductDtos;
	}

	@Override
	public GenericProductDto getProductById(String id) throws NoDataFoundException {
		FakeStoreProductDto product = fakeStoreProductServiceClient.getProductById(id);
	
		if (product == null) {
			throw new NoDataFoundException("Product Id: " + id + " doesn't exist.");
		}

		return convertFakeStoreProductIntoGenericProduct(product);
	}

	public GenericProductDto createProduct(GenericProductDto product) {

		return convertFakeStoreProductIntoGenericProduct(fakeStoreProductServiceClient.createProduct(product));
	}
	
	@Override
	public GenericProductDto updateProductById(String id, GenericProductDto product) {
		return convertFakeStoreProductIntoGenericProduct(fakeStoreProductServiceClient.updateProductById(id, product));
	}

	@Override
	public GenericProductDto deleteProductById(Long id) {

		return convertFakeStoreProductIntoGenericProduct(fakeStoreProductServiceClient.deleteProductById(id));
	}

}
