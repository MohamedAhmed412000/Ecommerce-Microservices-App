package com.project.ecommerce.product;

import com.project.ecommerce.exceptions.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductClient {

    @Value("${application.config.product-url}")
    private String productUrl;
    private final RestTemplate restTemplate;

    public List<Product> purchaseProducts(List<PurchaseRequest> purchaseRequest) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

        HttpEntity<List<PurchaseRequest>> requestEntity = new HttpEntity<>(purchaseRequest, headers);
        ParameterizedTypeReference<List<Product>> responseType = new ParameterizedTypeReference<>() {};
        ResponseEntity<List<Product>> responseEntity = restTemplate.exchange(
        productUrl + "/purchase", HttpMethod.POST, requestEntity, responseType
        );

        if (responseEntity.getStatusCode().isError()) {
            throw  new BusinessException("An error occurred while processing the purchase request: " +
                responseEntity.getStatusCode());
        }

        return responseEntity.getBody();
    }

    public Product getProductById(Integer productId) {
        ResponseEntity<Product> responseEntity = restTemplate.getForEntity(
        productUrl + "/{productId}", Product.class, productId
        );
        return responseEntity.getBody();
    }

}
