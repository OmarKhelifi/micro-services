package com.example.customer_service.service;

import java.util.List;

import com.example.customer_service.exception.CustomerException;
import org.springframework.stereotype.Service;

import com.example.customer_service.entity.Customer;
import com.example.customer_service.repository.CustomerRepository;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
    
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer getCustomerById(String id) {
        return customerRepository.findById(id).orElseThrow(() -> new CustomerException("Client non trouvé avec l'ID : " + id));
    }

    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }
}
