package sk.umb.example.library.customer.controller;

import org.springframework.web.bind.annotation.*;
import sk.umb.example.library.customer.service.CustomerDto;
import sk.umb.example.library.customer.service.CustomerRequestDto;
import sk.umb.example.library.customer.service.CustomerService;

import java.util.List;


@RestController
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/api/customers")
    public List<CustomerDto> searchCustomer(@RequestParam(required = false)String lastName){
        System.out.println("Search customer called.");

        return customerService.getAllCustomers();
    }
    @GetMapping("/api/customers/{customerId}")
    public CustomerDto getCustomer(@PathVariable Long customerId){
        System.out.println("Get customer called.");

        return customerService.getCustomerById(customerId);
    }

    @PostMapping("/api/customers")
    public Long createCustomer(@RequestBody CustomerRequestDto customer){
        System.out.println("Create customer called.");
        return customerService.createCustomer(customer);
    }

    @PutMapping("/api/customers/{customerId}")
    public void updateCustomer(@PathVariable Long customerId, @RequestBody CustomerRequestDto customer){
        System.out.println("Update customer called: ID = " + customerId);

        customerService.updateCustomer(customerId, customer);
    }

    @DeleteMapping("/api/customers/{customerId}")
    public void deleteCustomer(@PathVariable Long customerId){
        System.out.println("Delete customer called: ID = " + customerId);

        customerService.deleteCustomer(customerId);
    }

}
