package sk.umb.example.library.customer.controller;

import org.springframework.web.bind.annotation.*;

import java.sql.SQLOutput;
import java.util.Collections;
import java.util.List;


@RestController
public class CustomerController {
    @GetMapping("/api/customers")
    public void CustomerSearchDTO(@RequestParam(required = false) String lastName){
        System.out.println("Search customer called.");

    }

    @GetMapping("/api/customers/{customerId}")
    public void getCustomer(@PathVariable Long customerId) {
        System.out.println("Get customer called " + customerId);

    }

    @PostMapping("/api/customers")
    public void createCustomer(){
        System.out.println("Create customer called:");
    }

    @PutMapping("/api/customers/{customeId}")
    public void updateCustomer(@PathVariable Long customerId){
        System.out.println("Update customer called: ID = " + customerId);
    }

    @DeleteMapping("/api/customer/{customerId}")
    public void deleteCustomer(@PathVariable Long customerId){
        System.out.println("Delete customer called: ID = " + customerId);
    }
}
