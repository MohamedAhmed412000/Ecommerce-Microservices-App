package com.project.ecommerce.product;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/{productId}")
    public ResponseEntity<ProductResponse> getProductById(
        @PathVariable("productId") Integer productId
    ) {
        return ResponseEntity.ok(productService.getProductById(productId));
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @PostMapping
    public ResponseEntity<Integer> createProduct(
        @RequestBody @Valid ProductRequest productRequest
    ) {
        return ResponseEntity.ok(productService.createProduct(productRequest));
    }

    @PostMapping("/purchase")
    public ResponseEntity<List<ProductPurchaseResponse>> purchaseProduct(
            @RequestBody @Valid List<ProductPurchaseRequest> productPurchaseRequest
    ) {
        return ResponseEntity.ok(productService.purchaseProduct(productPurchaseRequest));
    }

}
