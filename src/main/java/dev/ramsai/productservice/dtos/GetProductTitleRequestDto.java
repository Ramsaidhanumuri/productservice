package dev.ramsai.productservice.dtos;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetProductTitleRequestDto {
	
	private List<String> uuids;
}
