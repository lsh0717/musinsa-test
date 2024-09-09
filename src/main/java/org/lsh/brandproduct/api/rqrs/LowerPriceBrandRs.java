package org.lsh.brandproduct.api.rqrs;

import java.util.List;

import org.lsh.brandproduct.domain.dto.CategoryItemDto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(staticName = "of")
@Getter
public class LowerPriceBrandRs {
	private String brandName;
	private List<CategoryItemDto> categoryItemList;
	private Long totalPrice;
}
