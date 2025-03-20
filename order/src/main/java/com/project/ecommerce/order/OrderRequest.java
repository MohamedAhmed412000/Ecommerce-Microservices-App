package com.project.ecommerce.order;

import com.project.ecommerce.product.PurchaseRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.List;

public record OrderRequest(
    Integer id,
    String reference,

    @Positive(message = "Payment amount must be positive")
    BigDecimal amount,

    @NotNull(message = "Payment method must be specified")
    PaymentMethodEnum paymentMethod,

    @NotNull(message = "Customer must be present")
    @NotEmpty(message = "Customer must be present")
    @NotBlank(message = "Customer must be present")
    String customerId,

    @NotEmpty(message = "You should al least purchase one product")
    List<PurchaseRequest> products
) {
}
