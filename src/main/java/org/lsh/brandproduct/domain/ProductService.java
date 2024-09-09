package org.lsh.brandproduct.domain;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.lsh.brandproduct.api.rqrs.LowerPriceBrandRs;
import org.lsh.brandproduct.api.rqrs.LowestPriceProductRs;
import org.lsh.brandproduct.api.rqrs.PriceCompareRs;
import org.lsh.brandproduct.domain.dto.BrandPriceDto;
import org.lsh.brandproduct.domain.dto.CategoryItemDto;
import org.lsh.brandproduct.domain.dto.ProductDto;
import org.lsh.brandproduct.domain.dto.BrandProductsAndTotalPriceDto;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
// @Transactional(readOnly = true) // DB 사용하면 트랜잭션 처리 필요
public class ProductService {
	private final BrandDao brandDao;
	private final ProductDao productDao;

	public LowestPriceProductRs getLowestPriceBrandByCategory() {
		List<ProductModel> lowerPriceProducts = Arrays.stream(ProductCategory.values())
			.map(productDao::findLowerPriceProductByCategory)
			.filter(Optional::isPresent)
			.map(Optional::get)
			.toList();
		long totalPrice = getTotalPrice(lowerPriceProducts);
		List<ProductDto> productDtoList = lowerPriceProducts.stream()
			.map(ProductDto::from)
			.toList();
		return LowestPriceProductRs.of(productDtoList, totalPrice);
	}

	public LowerPriceBrandRs getLowestPriceBrand() {
		BrandProductsAndTotalPriceDto productsAndTotalPriceDto = brandDao.findAll().stream()
			.map(brand -> {
				List<ProductModel> lowerPriceProducts = productDao.findAllLowerPriceProductOnEachCategoryByBrandName(brand.brandName());
				long totalPrice = getTotalPrice(lowerPriceProducts);
				return BrandProductsAndTotalPriceDto.of(brand.brandName(), lowerPriceProducts, totalPrice);
			})
			// category 별로 제품이 다 없으면 어떻게 제외하지? 카테고리를 Rq로 받으면 좋을텐데
			.min(Comparator.comparing(BrandProductsAndTotalPriceDto::getTotalPrice))
			.orElseThrow();// No Such Element Exception

		List<CategoryItemDto> categoryItemDtos = productsAndTotalPriceDto.getProducts().stream()
			.map(CategoryItemDto::from)
			.toList();
		return LowerPriceBrandRs.of(productsAndTotalPriceDto.getBrandName(), categoryItemDtos, productsAndTotalPriceDto.getTotalPrice());
	}

	private long getTotalPrice(List<ProductModel> productModels) {
		return productModels.stream()
			.map(ProductModel::price)
			.reduce(0, Integer::sum)
			.longValue();
	}

	public PriceCompareRs getPriceCompare(String category) {
		ProductCategory productCategory = ProductCategory.valueOf(category);

		ProductModel lowerPriceProduct = productDao.findLowerPriceProductByCategory(productCategory)
			.orElseThrow(() -> new IllegalArgumentException("해당 카테고리에 상품이 없습니다."));
		ProductModel higherPriceProduct = productDao.findHigherPriceProductByCategory(productCategory)
			.orElseThrow(() -> new IllegalArgumentException("해당 카테고리에 상품이 없습니다."));

		if (lowerPriceProduct.equals(higherPriceProduct)) {
			higherPriceProduct = null;
		}
		return PriceCompareRs.of(productCategory.getCategoryName(), BrandPriceDto.from(lowerPriceProduct), BrandPriceDto.from(higherPriceProduct));
	}
}
