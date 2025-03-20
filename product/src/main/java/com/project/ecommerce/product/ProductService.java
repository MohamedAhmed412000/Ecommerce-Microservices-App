package com.project.ecommerce.product;

import com.project.ecommerce.exceptions.ProductPurchaseException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public Integer createProduct(ProductRequest productRequest) {
        var product = productRepository.save(productMapper.getProduct(productRequest));
        return product.getId();
    }

    public List<ProductPurchaseResponse> purchaseProduct(List<ProductPurchaseRequest> productPurchaseRequest) {
        var productIds = productPurchaseRequest.stream().map(ProductPurchaseRequest::productId).toList();
        var storedProducts = productRepository.findAllByIdInOrderById(productIds);
        if (storedProducts.size() != productIds.size()) {
            throw new ProductPurchaseException("Not all products found in the database.");
        }
        var storesRequest = productPurchaseRequest.stream()
            .sorted(Comparator.comparing(ProductPurchaseRequest::productId))
            .toList();
        var purchasedProducts = new ArrayList<ProductPurchaseResponse>();
        for (int i = 0; i < storedProducts.size(); i++) {
            var product = storedProducts.get(i);
            var productRequest = storesRequest.get(i);
            if (product.getAvailableQuantity() < productRequest.quantity()) {
                throw new ProductPurchaseException("Insufficient stock quantity for product with id " + product.getId());
            }
            var newAvailableQuantity = product.getAvailableQuantity() - productRequest.quantity();
            product.setAvailableQuantity(newAvailableQuantity);
            productRepository.save(product);
            purchasedProducts.add(productMapper.getProductResponse(product, productRequest.quantity()));
        }
        return purchasedProducts;
    }

    public ProductResponse getProductById(Integer productId) {
        return productRepository.findById(productId).map(productMapper::getProductResponse)
            .orElseThrow(() -> new EntityNotFoundException("Product not found with id " + productId));
    }

    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll().stream().map(productMapper::getProductResponse).toList();
    }

}
