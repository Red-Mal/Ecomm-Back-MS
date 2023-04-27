package org.sid.customerservice.services;

import org.sid.customerservice.dtos.CustomerDto;
import org.sid.customerservice.entities.Customer;
import org.sid.customerservice.mappers.CustomerMapper;
import org.sid.customerservice.repositories.CustomerRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@Transactional
public class CustomerServiceImpl implements ICustomerService{

    private final CustomerRepository customerRepository;



    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    @Override
    public Customer createCustomer(CustomerDto customerDto) {
        return customerRepository.saveAndFlush(CustomerMapper.INSTANCE.customerDtoToCustomer(customerDto));
    }

    @Override
    public CustomerDto findById(Long id) {

        Customer customer=customerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found with id " + id));
        return CustomerMapper.INSTANCE.customerToCustomerDto(customer);
    }

    @Override
    public List<CustomerDto> findAll() {
        return CustomerMapper.INSTANCE.map(customerRepository.findAll());
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        customerRepository.findById(customer.getId())
                .orElseThrow(() -> new EntityNotFoundException("Customer not found with id " + customer.getId()));

        return customerRepository.save(customer);
    }

    @Override
    public void deleteCustomer(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found with id " + id));

        customerRepository.delete(customer);
    }

    @Override
    public Page<Customer> getCustomers(Pageable page) {
        return null;
    }









}
