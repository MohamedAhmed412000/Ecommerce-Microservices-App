package com.project.ecommerce.product;

import com.project.ecommerce.category.Category;
import org.springframework.stereotype.Service;

@Service
public class ProductMapper {
    public Product getProduct(ProductRequest productRequest) {
        return Product.builder()
            .id(productRequest.id())
            .name(productRequest.name())
            .description(productRequest.description())
            .availableQuantity(productRequest.availableQuantity())
            .price(productRequest.price())
            .category(
                Category.builder()
                .id(productRequest.categoryId())
                .build()
            )
            .build();
    }

    public ProductResponse getProductResponse(Product product) {
        return new ProductResponse(
            product.getId(),
            product.getName(),
            product.getDescription(),
            product.getAvailableQuantity(),
            product.getPrice(),
            product.getCategory().getId(),
            product.getCategory().getName(),
            product.getCategory().getDescription()
        );
    }

    public ProductPurchaseResponse getProductResponse(Product product, double quantity) {
        return new ProductPurchaseResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                quantity
        );
    }

}
