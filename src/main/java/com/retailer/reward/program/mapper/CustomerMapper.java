package com.retailer.reward.program.mapper;

import com.retailer.reward.program.dto.CustomerDto;
import com.retailer.reward.program.entity.postgres.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    Customer toRewards(CustomerDto customer);

    CustomerDto fromRewards(Customer customer);
}
