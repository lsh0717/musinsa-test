package org.lsh.brandproduct.domain;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.lsh.brandproduct.api.rqrs.PriceProductRs;
import org.lsh.brandproduct.domain.dto.ProductDto;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
// @Transactional(readOnly = true) // DB 사용하면 트랜잭션 처리 필요
public class ProductService {
	private final BrandDao brandDao;
	private final ProductDao productDao;

	public PriceProductRs getLowestPriceBrandByCategory() {
		List<ProductDto> productDtoList = Arrays.stream(ProductCategory.values())
			.map(productDao::findLowerPriceProductByCategory)
			.filter(Optional::isPresent)
			.map(Optional::get)
			.map(ProductDto::from)
			.collect(Collectors.toList());
		long totalPrice = productDtoList.stream()
			.map(ProductDto::getPrice)
			.reduce(0, Integer::sum)
			.longValue();
		return PriceProductRs.of(productDtoList, totalPrice);
	}
}
