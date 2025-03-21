package com.project.ecommerce.payment;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

@Validated
public record Customer(
    String id,

    @NotNull(message = "Customer firstname is required")
    String firstname,

    @NotNull(message = "Customer lastname is required")
    String lastname,

    @NotNull(message = "Customer email is required")
    @Email(message = "Invalid email address")
    String email
) {
}
