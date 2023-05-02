package kmprograms.model;

public record Invoice(Order order, Customer customer, String description) {
}
