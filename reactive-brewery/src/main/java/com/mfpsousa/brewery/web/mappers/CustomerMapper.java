package com.mfpsousa.brewery.web.mappers;

import com.mfpsousa.brewery.domain.Customer;
import com.mfpsousa.brewery.web.model.CustomerDto;
import org.mapstruct.Mapper;

@Mapper
public interface CustomerMapper {

    Customer customerDtoToCustomer(CustomerDto dto);

    CustomerDto customerToCustomerDto(Customer customer);
}
