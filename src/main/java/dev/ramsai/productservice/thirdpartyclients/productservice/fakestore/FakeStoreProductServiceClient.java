package dev.ramsai.productservice.thirdpartyclients.productservice.fakestore;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import dev.ramsai.productservice.dtos.GenericProductDto;
import dev.ramsai.productservice.exceptions.NoDataFoundException;

@Service
public class FakeStoreProductServiceClient {

	private RestTemplateBuilder restTemplateBuilder;

	@Value("${fakestore.api.url}")
	private String fakeStoreApiurl;

	@Value("${fakestore.api.paths.product}")
	private String fakeStoreProductsApiPath;

	private String specificProductRequestUrl;
	private String productRequestsBaseUrl;

	public FakeStoreProductServiceClient(RestTemplateBuilder restTemplateBuilder,
			@Value("${fakestore.api.url}") String fakeStoreApiUrl,
			@Value("${fakestore.api.paths.product}") String fakeStoreProductsApiPath) {

		this.restTemplateBuilder = restTemplateBuilder;
		this.productRequestsBaseUrl = fakeStoreApiUrl + fakeStoreProductsApiPath;
		this.specificProductRequestUrl = fakeStoreApiUrl + fakeStoreProductsApiPath + "/{id}";
	}

	public List<FakeStoreProductDto> getAllProducts() {

		RestTemplate restTemplate = restTemplateBuilder.build();
		ResponseEntity<FakeStoreProductDto[]> response = restTemplate.getForEntity(productRequestsBaseUrl,
				FakeStoreProductDto[].class);

		List<FakeStoreProductDto> fakeStoreProductDtos = Arrays.stream(response.getBody()).toList();

		return fakeStoreProductDtos;
	}

	public FakeStoreProductDto getProductById(String id) {

		RestTemplate restTemplate = restTemplateBuilder.build();
		ResponseEntity<FakeStoreProductDto> response = restTemplate.getForEntity(specificProductRequestUrl,
				FakeStoreProductDto.class, id);
		FakeStoreProductDto fakeStoreProductDto = response.getBody();

		return fakeStoreProductDto;
	}

	public FakeStoreProductDto createProduct(GenericProductDto product) {

		RestTemplate restTemplate = restTemplateBuilder.build();
		ResponseEntity<FakeStoreProductDto> response = restTemplate.postForEntity(productRequestsBaseUrl, product,
				FakeStoreProductDto.class);

		return response.getBody();
	}
	
	public FakeStoreProductDto updateProductById(String id, GenericProductDto product) {
		return null;
	}

	public FakeStoreProductDto deleteProductById(Long id) {

		RestTemplate restTemplate = restTemplateBuilder.build();
		ResponseEntity<FakeStoreProductDto> response = restTemplate.exchange(specificProductRequestUrl,
				HttpMethod.DELETE, null, FakeStoreProductDto.class, id);
		FakeStoreProductDto fakeStoreProductDto = response.getBody();

		return fakeStoreProductDto;
	}
}
