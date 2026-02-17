package com.example.customerservice.controllers;

import com.example.customerservice.entities.Customer;
import com.example.customerservice.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CustomerRestController {
    private CustomerService customerService;
    public  CustomerRestController(CustomerService customerService) {
        this.customerService = customerService;
    }
    @GetMapping("/customers")
    public List<Customer> getfindAll() {
        return customerService.getfindAll();
    }
    @GetMapping("/customer/{id}")
    public Customer findCustomerById(@PathVariable Long id) {
        return customerService.findCustomerById(id);

    }
    @PostMapping("/customers")
    public Customer SaveCustomer(Customer customer) {
        return customerService.SaveCustomer(customer);
    }

}
