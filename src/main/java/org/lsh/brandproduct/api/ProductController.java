package org.lsh.brandproduct.api;

import org.lsh.brandproduct.api.rqrs.PriceProductRs;
import org.lsh.brandproduct.domain.ProductCategory;
import org.lsh.brandproduct.domain.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ProductController {
	private final ProductService productService;

	/*
	구현 1) - 카테고리 별 최저가격 브랜드와 상품 가격, 총액을 조회하는 API
	API의 역할 : 한 카테고리 내에서 가성비 있으면서 멋스러운 제품을 찾는 고객들에게 최저 가격을 제공
                근데 총액은 왜 필요할까? 묶음 할인 같은 것을 제공하는 걸까?
	*/
	@GetMapping("/api/product/lowest-price-by-category")
	public PriceProductRs getLowestPriceBrandByCategory() {
		return productService.getLowestPriceBrandByCategory();
	}
}
