package sk.umb.example.library.customer.controller;

import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
public class CustomerController {
    @GetMapping("/api/customers")
    public void searchCustomer(@RequestParam(required = false)String lastName){
        System.out.println("Search customer called.");
    }

    @GetMapping("/api/customers/{customerId}")
    public void getCustomer(@PathVariable Long customerId){
        System.out.println("Get customer called.");
    }

    @PostMapping("/api/customers/{customerId}")
    public void createCustomer(){
        System.out.println("Create customer called.");
    }

    @PutMapping("/api/customers/{customerId}")
    public void updateCustomer(@PathVariable Long customerId){
        System.out.println("Update customer called: ID = " + customerId);
    }

    @DeleteMapping("/api/customers/{customerId}")
    public void deleteCustomer(@PathVariable Long customerId){
        System.out.println("Delete customer called: ID = " + customerId);
    }

}
