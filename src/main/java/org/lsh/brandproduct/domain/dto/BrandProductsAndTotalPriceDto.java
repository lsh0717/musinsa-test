package org.lsh.brandproduct.domain.dto;

import java.util.List;

import org.lsh.brandproduct.domain.ProductModel;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(staticName = "of")
@Getter
public class BrandProductsAndTotalPriceDto {
	private String brandName;
	private List<ProductModel> products;
	private Long totalPrice;
}
