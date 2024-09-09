package org.lsh.brandproduct.domain;

public record ProductModel(String brandName, ProductCategory category, double price, int quantity) {
}