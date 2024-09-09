package org.lsh.brandproduct.domain.dto;

import java.util.Objects;

import org.lsh.brandproduct.domain.ProductModel;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(staticName = "of")
@Getter
public class BrandPriceDto {
	private String brandName;
	private Integer price;

	public static BrandPriceDto from(ProductModel lowerPriceProduct) {
		if (Objects.isNull(lowerPriceProduct)) {
			return null;
		}
		return BrandPriceDto.of(lowerPriceProduct.brandName(), lowerPriceProduct.price());
	}
}
