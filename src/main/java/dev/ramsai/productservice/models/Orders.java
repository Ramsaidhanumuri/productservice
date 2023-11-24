package dev.ramsai.productservice.models;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "orders")
@Getter
@Setter
public class Orders extends BaseModel{

	@ManyToMany
	private List<Product> product;
}
