package org.lsh.brandproduct.domain;

public record ProductModel(String brandName, ProductCategory category, Integer price, int quantity) {
}