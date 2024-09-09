package org.lsh.brandproduct.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ProductCategory {
	TOP("상의"),
	OUTER("아우터"),
	PANTS("하의"),
	SNEAKERS("스니커즈"),
	BAG("가방"),
	HAT("모자"),
	SOCKS("양말"),
	ACCESSORY("액세서리"),
	;

	private final String categoryName;
}

