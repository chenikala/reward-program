package com.retailer.reward.program.service;

import com.retailer.reward.program.dto.CustomerDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * Reward program service interface
 */
public interface CustomerService {
    public List<CustomerDto> getAllCustomers();

    Page<CustomerDto> getAllCustomersAsPageBy(Pageable pageable);

    public CustomerDto getCustomer(Long customerId);
    public CustomerDto insertCustomer(@RequestBody CustomerDto customer);
    public CustomerDto updateCustomer(@RequestBody CustomerDto customer);
    public String deleteCustomer(Long customerId);
}
