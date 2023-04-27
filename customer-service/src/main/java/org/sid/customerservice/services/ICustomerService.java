package org.sid.customerservice.services;

import org.sid.customerservice.dtos.CustomerDto;
import org.sid.customerservice.entities.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICustomerService {
    Customer createCustomer(CustomerDto customerDto);
    CustomerDto findById(Long id);
    List<CustomerDto> findAll();
    Customer updateCustomer(Customer customer);
    void deleteCustomer(Long id);
    Page<Customer> getCustomers(Pageable page);


}
