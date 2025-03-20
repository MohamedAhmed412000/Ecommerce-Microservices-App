package com.project.ecommerce.customer;

import com.project.ecommerce.exceptions.CustomerNotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public String createCustomer(CustomerRequest customerRequest) {
        var customer = customerRepository.save(customerMapper.getCustomer(customerRequest));
        return customer.getId();
    }

    public void updateCustomer(CustomerRequest customerRequest) {
        var customer = customerRepository.findById(customerRequest.id()).orElseThrow(
            () -> new CustomerNotFoundException("No customer is found with id=" + customerRequest.id()));
        mergerCustomer(customer, customerRequest);
        customerRepository.save(customer);
    }

    private void mergerCustomer(Customer customer, CustomerRequest customerRequest) {
        if (StringUtils.isNotBlank(customer.getFirstname())) {
            customer.setFirstname(customerRequest.firstname());
        }
        if (StringUtils.isNotBlank(customer.getLastname())) {
            customer.setLastname(customerRequest.lastname());
        }
        if (StringUtils.isNotBlank(customer.getEmail())) {
            customer.setEmail(customerRequest.email());
        }
        if (customerRequest.address() != null) {
            customer.setAddress(customerRequest.address());
        }

    }

    public List<CustomerResponse> getAllCustomers() {
        return customerRepository.findAll().stream()
           .map(customerMapper::getCustomerResponse)
           .collect(toList());
    }

    public Boolean customerExistsById(String customerId) {
        return customerRepository.existsById(customerId);
    }

    public CustomerResponse getCustomerById(String customerId) {
        var customer = customerRepository.findById(customerId).orElseThrow(
            () -> new CustomerNotFoundException("No customer is found with id=" + customerId));
        return customerMapper.getCustomerResponse(customer);
    }

    public void deleteCustomer(String customerId) {
        customerRepository.deleteById(customerId);
    }
}
