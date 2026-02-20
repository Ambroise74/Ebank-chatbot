package com.example.customerservice.service;

import com.example.customerservice.entities.Customer;
import com.example.customerservice.repository.CustomerRepository;
import org.springaicommunity.mcp.annotation.McpTool;
import org.springaicommunity.mcp.annotation.McpToolParam;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    private CustomerRepository customerRepository;
     public CustomerService(CustomerRepository customerRepository) {
         this.customerRepository = customerRepository;
     }
    @McpTool(description = "Get All customers")
     public List<Customer> getfindAll() {
         return customerRepository.findAll();
     }
    @McpTool(description = "Find customerByid")
     public Customer findCustomerById( @McpToolParam(description = "the customers id") Long id) {
         return customerRepository.findById(id)
                 .orElseThrow(()->new RuntimeException("Customer not found!"));
     }
     @McpTool(description = "save a new customer")
   public Customer SaveCustomer(@McpToolParam(description = "the customers(nom, email)") Customer customer) {
       return customerRepository.save(customer);
   }

}
