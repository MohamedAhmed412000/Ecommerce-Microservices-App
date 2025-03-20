package com.project.ecommerce.customer;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping
    public ResponseEntity<List<CustomerResponse>> getAllCustomers() {
        return ResponseEntity.ok(customerService.getAllCustomers());
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerResponse> getCustomerById(
        @PathVariable("customerId") String customerId
    ) {
        return customerService.customerExistsById(customerId)
           ? ResponseEntity.ok(customerService.getCustomerById(customerId))
            : ResponseEntity.notFound().build();
    }

    @GetMapping("/exists/{customerId}")
    public ResponseEntity<Boolean> customerExistsById(
        @PathVariable("customerId") String customerId
    ) {
        return ResponseEntity.ok(customerService.customerExistsById(customerId));
    }

    @PostMapping
    public ResponseEntity<String> createCustomer(
        @Valid @RequestBody CustomerRequest customerRequest
    ) {
        return ResponseEntity.ok(customerService.createCustomer(customerRequest));
    }

    @PutMapping
    public ResponseEntity<Void> updateCustomer(
        @Valid @RequestBody CustomerRequest customerRequest
    ) {
        customerService.updateCustomer(customerRequest);
        return ResponseEntity.accepted().build();
    }

    @DeleteMapping("/{customerId}")
    public ResponseEntity<Void> deleteCustomer(
        @PathVariable("customerId") String customerId
    ) {
        if (customerService.customerExistsById(customerId)) {
            customerService.deleteCustomer(customerId);
        }
        return ResponseEntity.noContent().build();
    }

}
