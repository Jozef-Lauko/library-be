package sk.umb.example.library.customer.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {
    private final List<CustomerDto> customers = new ArrayList<>();

    public List<CustomerDto> getAllCustomers() {
        return customers;
    }

    public CustomerDto getCustomerById(Long customerId) {
        int index = customerId.intValue();

        if (index >= customers.size()){
            return new CustomerDto();
        }

        return customers.get(customerId.intValue());
    }

    public Long createCustomer(CustomerRequestDto customer) {
        Long customerId = (long) customers.size();

        CustomerDto customerDto = mapToCustomerDto(customer);
        customerDto.setId(customerId);

        customers.add(customerDto);

        return customerId;
    }

    private static CustomerDto mapToCustomerDto(CustomerRequestDto customer){
        CustomerDto customerDto = new CustomerDto();

        customerDto.setFirstName(customer.getFirstName());
        customerDto.setLastName(customer.getLastName());
        customerDto.setContact(customerDto.getContact());

        return customerDto;
    }

    public void updateCustomer(Long customerId, CustomerRequestDto customer) {

    }

    public void deleteCustomer(Long customerId) {
        customers.remove(customerId.intValue());
    }
}
