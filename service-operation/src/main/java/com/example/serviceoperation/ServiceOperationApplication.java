package com.example.serviceoperation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients  // 👈 obligatoire
public class ServiceOperationApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceOperationApplication.class, args);
    }

}
