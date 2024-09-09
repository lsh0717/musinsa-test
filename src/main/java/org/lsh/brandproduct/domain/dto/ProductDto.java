package org.lsh.brandproduct.domain.dto;

import org.lsh.brandproduct.domain.ProductCategory;
import org.lsh.brandproduct.domain.ProductModel;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ProductDto {
	private String brandName;
	private String categoryName;
	private Integer price;
	// private String productUrl;

	public static ProductDto from(ProductModel productModel) {
		return new ProductDto(productModel.brandName(), productModel.category().getCategoryName(), productModel.price());
	}
}
