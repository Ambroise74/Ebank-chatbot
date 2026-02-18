package com.example.ebankservice.feign;

import com.example.ebankservice.modele.Customer;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "customer-service")
public interface CustomersRestClient {
    @GetMapping("customer/{id}")
    Customer getCustomerById(@PathVariable Long id);


}
