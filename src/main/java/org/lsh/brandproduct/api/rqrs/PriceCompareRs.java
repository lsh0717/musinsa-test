package org.lsh.brandproduct.api.rqrs;

import org.lsh.brandproduct.domain.dto.BrandPriceDto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(staticName = "of")
@Getter
public class PriceCompareRs {
	private String categoryName;
	private BrandPriceDto lowestPriceBrand;
	private BrandPriceDto highestPriceBrand;
}
