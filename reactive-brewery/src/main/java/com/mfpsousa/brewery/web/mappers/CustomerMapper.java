package com.mfpsousa.brewery.web.mappers;

import com.mfpsousa.brewery.domain.Customer;
import com.mfpsousa.brewery.web.model.CustomerDto;
import org.mapstruct.Mapper;

/**
 * Created by jt on 2019-05-25.
 */
@Mapper
public interface CustomerMapper {

    Customer customerDtoToCustomer(CustomerDto dto);

    CustomerDto customerToCustomerDto(Customer customer);
}
