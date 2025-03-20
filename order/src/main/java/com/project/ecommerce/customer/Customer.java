package com.project.ecommerce.customer;

public record Customer(
    String id,
    String firstname,
    String lastname,
    String email
) {
}
