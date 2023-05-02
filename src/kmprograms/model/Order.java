package kmprograms.model;

import java.math.BigDecimal;

public record Order(int id, String product, int quantity, BigDecimal price) {
}
