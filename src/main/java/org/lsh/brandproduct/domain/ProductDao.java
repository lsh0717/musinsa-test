package org.lsh.brandproduct.domain;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

/*
 * 상품 정보를 담당하는 DAO 클래스
 * 상품 품절은 없음. 외부에서 API를 통해 CRUD 가능
 */
@Component
public class ProductDao {
	private final List<ProductModel> productModelList;

	public void add(ProductModel productModel) {
		if (this.findByBrandNameAndProductCategory(productModel.brandName(), productModel.category()).isPresent()) {
			throw new IllegalArgumentException("이미 존재하는 상품입니다.");
		}
		productModelList.add(productModel);
	}

	public void update(ProductModel productModel) {
		productModelList.stream()
			.filter(product -> product.brandName().equals(productModel.brandName()) && product.category().equals(productModel.category()))
			.findFirst()
			.ifPresentOrElse(
				product -> productModelList.set(productModelList.indexOf(product), productModel),
				() -> {
					throw new IllegalArgumentException("존재하지 않는 상품입니다.");
				});
	}

	public void delete(String brandName, ProductCategory productCategory) {
		findByBrandNameAndProductCategory(brandName, productCategory)
			.ifPresent(productModelList::remove);
	}

	public Optional<ProductModel> findByBrandNameAndProductCategory(String brandName, ProductCategory productCategory) {
		return productModelList.stream()
				.filter(productModel -> productModel.brandName().equals(brandName) && productModel.category().equals(productCategory))
				.findFirst();
	}

	public Optional<ProductModel> findLowerPriceProductByCategory(ProductCategory productCategory) {
		return productModelList.stream()
				.filter(productModel -> productModel.category().equals(productCategory))
				.min(Comparator.comparing(ProductModel::price));
	}

	public Optional<ProductModel> findHigherPriceProductByCategory(ProductCategory productCategory) {
		return productModelList.stream()
			.filter(productModel -> productModel.category().equals(productCategory))
			.max(Comparator.comparing(ProductModel::price));
	}

	public List<ProductModel> findAllLowerPriceProductOnEachCategoryByBrandName(String brandName) {
		Map<ProductCategory, List<ProductModel>> productCategoryListMap = productModelList.stream()
			.filter(productModel -> productModel.brandName().equals(brandName))
			.collect(Collectors.groupingBy(ProductModel::category));
		return productCategoryListMap.values().stream()
			.map(productListOnSameCategory -> productListOnSameCategory.stream()
				.min(Comparator.comparing(ProductModel::price)))
			.filter(Optional::isPresent)
			.map(Optional::get)
			.toList();
	}

	public ProductDao() {
		productModelList = new ArrayList<>();
		productModelList.add(new ProductModel("A", ProductCategory.TOP, 11200, 1));
		productModelList.add(new ProductModel("A", ProductCategory.OUTER, 5500, 1));
		productModelList.add(new ProductModel("A", ProductCategory.PANTS, 4200, 1));
		productModelList.add(new ProductModel("A", ProductCategory.SNEAKERS, 9000, 1));
		productModelList.add(new ProductModel("A", ProductCategory.BAG, 2000, 1));
		productModelList.add(new ProductModel("A", ProductCategory.HAT, 1700, 1));
		productModelList.add(new ProductModel("A", ProductCategory.SOCKS, 1800, 1));
		productModelList.add(new ProductModel("A", ProductCategory.ACCESSORY, 2300, 1));

		productModelList.add(new ProductModel("B", ProductCategory.TOP, 10500, 1));
		productModelList.add(new ProductModel("B", ProductCategory.OUTER, 5900, 1));
		productModelList.add(new ProductModel("B", ProductCategory.PANTS, 3800, 1));
		productModelList.add(new ProductModel("B", ProductCategory.SNEAKERS, 9100, 1));
		productModelList.add(new ProductModel("B", ProductCategory.BAG, 2100, 1));
		productModelList.add(new ProductModel("B", ProductCategory.HAT, 2000, 1));
		productModelList.add(new ProductModel("B", ProductCategory.SOCKS, 2000, 1));
		productModelList.add(new ProductModel("B", ProductCategory.ACCESSORY, 2200, 1));

		productModelList.add(new ProductModel("C", ProductCategory.TOP, 10000, 1));
		productModelList.add(new ProductModel("C", ProductCategory.OUTER, 6200, 1));
		productModelList.add(new ProductModel("C", ProductCategory.PANTS, 3300, 1));
		productModelList.add(new ProductModel("C", ProductCategory.SNEAKERS, 9200, 1));
		productModelList.add(new ProductModel("C", ProductCategory.BAG, 2200, 1));
		productModelList.add(new ProductModel("C", ProductCategory.HAT, 1900, 1));
		productModelList.add(new ProductModel("C", ProductCategory.SOCKS, 2200, 1));
		productModelList.add(new ProductModel("C", ProductCategory.ACCESSORY, 2100, 1));

		productModelList.add(new ProductModel("D", ProductCategory.TOP, 10100, 1));
		productModelList.add(new ProductModel("D", ProductCategory.OUTER, 5100, 1));
		productModelList.add(new ProductModel("D", ProductCategory.PANTS, 3000, 1));
		productModelList.add(new ProductModel("D", ProductCategory.SNEAKERS, 9500, 1));
		productModelList.add(new ProductModel("D", ProductCategory.BAG, 2500, 1));
		productModelList.add(new ProductModel("D", ProductCategory.HAT, 1500, 1));
		productModelList.add(new ProductModel("D", ProductCategory.SOCKS, 2400, 1));
		productModelList.add(new ProductModel("D", ProductCategory.ACCESSORY, 2000, 1));

		productModelList.add(new ProductModel("E", ProductCategory.TOP, 10700, 1));
		productModelList.add(new ProductModel("E", ProductCategory.OUTER, 5000, 1));
		productModelList.add(new ProductModel("E", ProductCategory.PANTS, 3800, 1));
		productModelList.add(new ProductModel("E", ProductCategory.SNEAKERS, 9900, 1));
		productModelList.add(new ProductModel("E", ProductCategory.BAG, 2300, 1));
		productModelList.add(new ProductModel("E", ProductCategory.HAT, 1800, 1));
		productModelList.add(new ProductModel("E", ProductCategory.SOCKS, 2100, 1));
		productModelList.add(new ProductModel("E", ProductCategory.ACCESSORY, 2100, 1));

		productModelList.add(new ProductModel("F", ProductCategory.TOP, 11200, 1));
		productModelList.add(new ProductModel("F", ProductCategory.OUTER, 7200, 1));
		productModelList.add(new ProductModel("F", ProductCategory.PANTS, 4000, 1));
		productModelList.add(new ProductModel("F", ProductCategory.SNEAKERS, 9300, 1));
		productModelList.add(new ProductModel("F", ProductCategory.BAG, 2100, 1));
		productModelList.add(new ProductModel("F", ProductCategory.HAT, 1600, 1));
		productModelList.add(new ProductModel("F", ProductCategory.SOCKS, 2300, 1));
		productModelList.add(new ProductModel("F", ProductCategory.ACCESSORY, 1900, 1));

		productModelList.add(new ProductModel("G", ProductCategory.TOP, 10500, 1));
		productModelList.add(new ProductModel("G", ProductCategory.OUTER, 5800, 1));
		productModelList.add(new ProductModel("G", ProductCategory.PANTS, 3900, 1));
		productModelList.add(new ProductModel("G", ProductCategory.SNEAKERS, 9000, 1));
		productModelList.add(new ProductModel("G", ProductCategory.BAG, 2200, 1));
		productModelList.add(new ProductModel("G", ProductCategory.HAT, 1700, 1));
		productModelList.add(new ProductModel("G", ProductCategory.SOCKS, 2100, 1));
		productModelList.add(new ProductModel("G", ProductCategory.ACCESSORY, 2000, 1));

		productModelList.add(new ProductModel("H", ProductCategory.TOP, 10800, 1));
		productModelList.add(new ProductModel("H", ProductCategory.OUTER, 6300, 1));
		productModelList.add(new ProductModel("H", ProductCategory.PANTS, 3100, 1));
		productModelList.add(new ProductModel("H", ProductCategory.SNEAKERS, 9700, 1));
		productModelList.add(new ProductModel("H", ProductCategory.BAG, 2100, 1));
		productModelList.add(new ProductModel("H", ProductCategory.HAT, 1600, 1));
		productModelList.add(new ProductModel("H", ProductCategory.SOCKS, 2000, 1));
		productModelList.add(new ProductModel("H", ProductCategory.ACCESSORY, 2000, 1));

		productModelList.add(new ProductModel("I", ProductCategory.TOP, 11400,1));
		productModelList.add(new ProductModel("I", ProductCategory.OUTER, 6700, 1));
		productModelList.add(new ProductModel("I", ProductCategory.PANTS, 3200, 1));
		productModelList.add(new ProductModel("I", ProductCategory.SNEAKERS, 9500, 1));
		productModelList.add(new ProductModel("I", ProductCategory.BAG, 2400, 1));
		productModelList.add(new ProductModel("I", ProductCategory.HAT, 1700, 1));
		productModelList.add(new ProductModel("I", ProductCategory.SOCKS, 1700, 1));
		productModelList.add(new ProductModel("I", ProductCategory.ACCESSORY, 2400, 1));
	}
}
