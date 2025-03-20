package com.project.ecommerce.orderLine;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.Set;

public interface OrderLineRepository extends JpaRepository<OrderLine, Integer> {

    @Query("SELECT DISTINCT ol FROM OrderLine ol WHERE ol.order.id = :orderId")
    Set<OrderLine> findAllByOrderId(Integer orderId);

}
