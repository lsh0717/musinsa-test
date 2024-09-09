package org.lsh.brandproduct.api;

import org.lsh.brandproduct.api.rqrs.LowerPriceBrandRs;
import org.lsh.brandproduct.api.rqrs.LowestPriceProductRs;
import org.lsh.brandproduct.api.rqrs.PriceCompareRs;
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
	public LowestPriceProductRs getLowestPriceBrandByCategory() {
		return productService.getLowestPriceBrandByCategory();
	}

	/*
	단일 브랜드로 모든 카테고리 상품을 구매할 때 최저가격에 판매하는 브랜드와 카테고리의 상품가격, 총액을 조회하는 API
	API의 역할 : 한 브랜드에서 다양한 카테고리의 상품을 구매할 때 최저 가격을 제공
	           category 리스트를 받아서 해당 카테고리에 속한 제품들의 최저 가격을 제공하면 어떨까.
	 */
	@GetMapping("/api/product/lowest-price-brand")
	public LowerPriceBrandRs getLowestPriceBrand() {
		return productService.getLowestPriceBrand();
	}

	/*
	카테고리 이름으로 최저, 최고 가격 브랜드와 상품 가격을 조회하는 API
	API의 역할 : 가격 비교, 카테고리 이름을 받아서 해당 카테고리의 최저, 최고 가격 브랜드와 상품 가격을 제공
	 */
	@GetMapping("/api/product/price-compare")
	public PriceCompareRs getPriceCompare(@RequestParam String category) {
		return productService.getPriceCompare(category);
	}
}
