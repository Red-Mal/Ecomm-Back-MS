package org.sid.customerservice.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.sid.customerservice.dtos.CustomerDto;
import org.sid.customerservice.entities.Customer;

import java.util.List;

@Mapper
public interface CustomerMapper {

    CustomerMapper INSTANCE= Mappers.getMapper(CustomerMapper.class);

    CustomerDto customerToCustomerDto(Customer customer);
    List<CustomerDto> map(List<Customer> Customers);
    Customer customerDtoToCustomer(CustomerDto customerDto);
}
