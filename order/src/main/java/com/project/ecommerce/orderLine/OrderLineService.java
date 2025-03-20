package com.project.ecommerce.orderLine;

import com.project.ecommerce.product.ProductClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderLineService {

    private final OrderLineRepository orderLineRepository;
    private final OrderLineMapper orderLineMapper;
    private final ProductClient productClient;

    public Integer createOrderLine(OrderLineRequest orderLineRequest) {
        var orderLine = orderLineRepository.save(orderLineMapper.getOrderLine(orderLineRequest));
        return orderLine.getId();
    }

    public List<OrderLineResponse> findAllByOrderId(Integer orderId) {

        return orderLineRepository.findAllByOrderId(orderId).stream()
           .map(orderLine -> {
               var product = productClient.getProductById(Integer.valueOf(orderLine.getProductId()));
               return orderLineMapper.getOrderLineResponse(orderLine, product);
           }).toList();
    }
}
