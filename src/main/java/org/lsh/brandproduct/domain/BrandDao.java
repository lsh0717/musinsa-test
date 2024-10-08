package org.lsh.brandproduct.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

/*
 * 브랜드 정보를 담당하는 DAO 클래스
 * 외부에서 API를 통해 CRUD 가능
 */
@Component
public class BrandDao {
	private final List<BrandModel> brandList;

	public void add(BrandModel brandModel) {
		if (this.findByName(brandModel.brandName()).isPresent()) {
			throw new IllegalArgumentException("이미 존재하는 브랜드입니다.");
		}
		brandList.add(brandModel);
	}

	public void update(BrandModel brandModel) {
		brandList.stream()
			.filter(brand -> brand.brandName().equals(brandModel.brandName()))
			.findFirst()
			.ifPresentOrElse(
				brand -> brandList.set(brandList.indexOf(brand), brandModel),
				() -> {
					throw new IllegalArgumentException("존재하지 않는 브랜드입니다.");
				});
	}

	public void delete(String brandName) {
		this.findByName(brandName)
			.ifPresent(brandList::remove);
	}


	public List<BrandModel> findAll() {
		// find All 일 때는 read only list로 주기
		return Collections.unmodifiableList(brandList);
	}

	public Optional<BrandModel> findByName(String name) {
		return brandList.stream()
			.filter(brandModel -> brandModel.brandName().equals(name))
			.findFirst();
	}

	public BrandDao() {
		brandList = new ArrayList<>();
		brandList.add(new BrandModel("A"));
		brandList.add(new BrandModel("B"));
		brandList.add(new BrandModel("C"));
		brandList.add(new BrandModel("D"));
		brandList.add(new BrandModel("E"));
		brandList.add(new BrandModel("F"));
		brandList.add(new BrandModel("G"));
		brandList.add(new BrandModel("H"));
		brandList.add(new BrandModel("I"));
	}
}