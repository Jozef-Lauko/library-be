package sk.umb.example.library.customer.service;

import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class CustomerService {
    private final AtomicLong lastIndex = new AtomicLong(0);

    private final Map<Long, CustomerDetailDto> customerDatabase = new HashMap();

    public List<CustomerDetailDto> getAllCustomers() {
        return new ArrayList<>(customerDatabase.values());
    }

    public List<CustomerDetailDto> searchCustomerByLastName(String lastName) {
        return customerDatabase.values().stream()
                                        .filter(dto -> lastName.equals(dto.getLastName()))
                                        .toList();
    }

    public CustomerDetailDto getCustomerById(Long customerId) {
        validateCustomerExists(customerId);

        return customerDatabase.get(customerId);
    }

    public Long createCustomer(CustomerRequestDto customerRequestDTO) {
        CustomerDetailDto customerDetailDTO = mapToCustomerDetailDTO(lastIndex.getAndIncrement(),
                                                                     customerRequestDTO);

        customerDatabase.put(customerDetailDTO.getId(), customerDetailDTO);

        return customerDetailDTO.getId();
    }

    private static CustomerDetailDto mapToCustomerDetailDTO(Long index, CustomerRequestDto customerRequestDTO) {
        CustomerDetailDto dto = new CustomerDetailDto();

        dto.setId(index);
        dto.setLastName(customerRequestDTO.getLastName());
        dto.setFirstName(customerRequestDTO.getFirstName());
        dto.setEmailContact(customerRequestDTO.getEmailContact());

        return dto;
    }

    public void updateCustomer(Long customerId, CustomerRequestDto customerRequestDTO) {
        validateCustomerExists(customerId);

        CustomerDetailDto customerDetailDTO = customerDatabase.get(customerId);

        if (! Strings.isEmpty(customerRequestDTO.getFirstName())) {
            customerDetailDTO.setFirstName(customerRequestDTO.getFirstName());
        }

        if (! Strings.isEmpty(customerRequestDTO.getLastName())) {
            customerDetailDTO.setLastName(customerRequestDTO.getLastName());
        }

        if (! Strings.isEmpty(customerRequestDTO.getEmailContact())) {
            customerDetailDTO.setEmailContact(customerRequestDTO.getEmailContact());
        }
    }

    private void validateCustomerExists(Long customerId) {
        if (! customerDatabase.containsKey(customerId)) {
            throw new IllegalArgumentException("CustomerId: " + customerId + " does not exists!");
        }
    }

    public void deleteCustomer(Long customerId) {
        customerDatabase.remove(customerId);
    }
}
