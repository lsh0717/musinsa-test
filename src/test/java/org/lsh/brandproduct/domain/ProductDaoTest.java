package org.lsh.brandproduct.domain;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.lsh.brandproduct.domain.ProductCategory;
import org.lsh.brandproduct.domain.ProductDao;
import org.lsh.brandproduct.domain.ProductModel;

import static org.junit.jupiter.api.Assertions.*;

class ProductDaoTest {

	@Test
	void findByBrandNameAndProductCategory_조회될_때() {
		ProductDao productDao = new ProductDao();
		Optional<ProductModel> product = productDao.findByBrandNameAndProductCategory("A", ProductCategory.TOP);
		assertFalse(product.isEmpty());
	}

	@Test
	void findByBrandNameAndProductCategory_데이터_없을_때() {
		ProductDao productDao = new ProductDao();
		Optional<ProductModel> product = productDao.findByBrandNameAndProductCategory("NO_DATA", ProductCategory.TOP);
		assertTrue(product.isEmpty());
	}

	@Test
	void add() {
		ProductDao productDao = new ProductDao();
		ProductModel productModel = new ProductModel("나이키", ProductCategory.SNEAKERS, 100000, 10);
		productDao.add(productModel);

		productDao.findByBrandNameAndProductCategory(productModel.brandName(), productModel.category())
			.ifPresentOrElse(
				product -> assertEquals(productModel, product),
				() -> fail("상품이 추가 테스트 실패"));
	}

	@Test
	void add_이미_존재하는_상품() {
		ProductDao productDao = new ProductDao();
		ProductModel productModel = new ProductModel("A", ProductCategory.TOP, 11200, 1);
		assertThrows(IllegalArgumentException.class, () -> productDao.add(productModel));
	}

	@Test
	void update() {
		ProductDao productDao = new ProductDao();
		ProductModel productModel = new ProductModel("A", ProductCategory.TOP, 11200, 1);
		ProductModel updatedProductModel = new ProductModel("A", ProductCategory.TOP, 20000, 10);
		productDao.update(updatedProductModel);

		productDao.findByBrandNameAndProductCategory(productModel.brandName(), productModel.category())
			.ifPresentOrElse(
				product -> assertEquals(updatedProductModel, product),
				() -> fail("상품 수정 테스트 실패"));
	}

	@Test
	void update_존재하지_않는_상품() {
		ProductDao productDao = new ProductDao();
		ProductModel productModel = new ProductModel("NO_DATA", ProductCategory.TOP, 11200, 1);
		assertThrows(IllegalArgumentException.class, () -> productDao.update(productModel));
	}

	@Test
	void delete() {
		ProductDao productDao = new ProductDao();
		ProductModel productModel = new ProductModel("A", ProductCategory.TOP, 11200, 1);
		productDao.delete(productModel.brandName(), productModel.category());

		Optional<ProductModel> product = productDao.findByBrandNameAndProductCategory(productModel.brandName(), productModel.category());
		assertTrue(product.isEmpty());
	}
}