package org.lsh.brandproduct.api.rqrs;

import java.util.List;

import org.lsh.brandproduct.domain.dto.ProductDto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(staticName = "of")
public class PriceProductRs {
	private List<ProductDto> productList;
	private Long totalPrice;
}
