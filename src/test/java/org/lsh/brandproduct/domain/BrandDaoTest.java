package org.lsh.brandproduct.domain;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BrandDaoTest {
	@Test
	void findByName() {
		BrandDao brandDao = new BrandDao();
		Optional<BrandModel> brandModelOptional = brandDao.findByName("A");
		assertFalse(brandModelOptional.isEmpty());
	}

	@Test
	void findByName_데이터_없을_때() {
		BrandDao brandDao = new BrandDao();
		Optional<BrandModel> brandModelOptional = brandDao.findByName("A");
		assertTrue(brandModelOptional.isEmpty());
	}

	@Test
	void add() {
		BrandDao brandDao = new BrandDao();
		BrandModel brandModel = new BrandModel("나이키");
		brandDao.add(brandModel);

		brandDao.findByName(brandModel.brandName())
			.ifPresentOrElse(
				brand -> assertEquals(brandModel, brand),
				() -> fail("브랜드 추가 테스트 실패"));
	}

	@Test
	void add_이미_존재하는_브랜드() {
		BrandDao brandDao = new BrandDao();
		BrandModel brandModel = new BrandModel("A");
		assertThrows(IllegalArgumentException.class, () -> brandDao.add(brandModel));
	}

	@Test
	void update() {
		BrandDao brandDao = new BrandDao();
		BrandModel brandModel = new BrandModel("A");
		brandDao.update(brandModel);

		brandDao.findByName(brandModel.brandName())
			.ifPresentOrElse(
				brand -> assertEquals(brandModel, brand),
				() -> fail("브랜드 업데이트 테스트 실패"));
	}

	@Test
	void update_존재하지_않는_브랜드() {
		BrandDao brandDao = new BrandDao();
		BrandModel brandModel = new BrandModel("NO_DATA");
		assertThrows(IllegalArgumentException.class, () -> brandDao.update(brandModel));
	}

	@Test
	void delete() {
		BrandDao brandDao = new BrandDao();
		BrandModel brandModel = new BrandModel("A");
		brandDao.delete(brandModel.brandName());

		Optional<BrandModel> brandModelOptional = brandDao.findByName(brandModel.brandName());
		assertTrue(brandModelOptional.isEmpty());
	}
}