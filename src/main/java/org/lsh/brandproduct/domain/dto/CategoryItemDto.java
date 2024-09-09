package org.lsh.brandproduct.domain.dto;

import org.lsh.brandproduct.domain.ProductModel;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(staticName = "of")
@Getter
public class CategoryItemDto {
	private String categoryName;
	private Integer price;

	public static CategoryItemDto from(ProductModel productModel) {
		return CategoryItemDto.of(productModel.category().getCategoryName(), productModel.price());
	}
}
