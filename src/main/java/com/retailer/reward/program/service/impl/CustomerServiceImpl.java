package com.retailer.reward.program.service.impl;

import com.retailer.reward.program.dto.CustomerDto;
import com.retailer.reward.program.entity.postgres.Customer;
import com.retailer.reward.program.mapper.CustomerMapper;
import com.retailer.reward.program.repository.postgres.CustomerRepository;
import com.retailer.reward.program.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Customer service implementation
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    private final CustomerMapper mapper;

    @Override
    public List<CustomerDto> getAllCustomers() {
        log.info("get all customer data CustomerServiceImpl::getAllCustomers");
        List<Customer> rewardsList = customerRepository.findAll();
        return rewardsList.stream().map(mapper::fromRewards).collect(Collectors.toList());
    }

    @Override
    public Page<CustomerDto> getAllCustomersAsPageBy(Pageable pageable) {
        log.info("get reward summary from CustomerServiceImpl::getAllCustomersAsPageBy");
        Page<Customer> page = customerRepository.findAll(pageable);
        Function<Customer, CustomerDto> converterFunction = entity -> {
            return new CustomerDto(entity.getCid(),
                    entity.getName(),
                    entity.getAge(),
                    entity.getPhonenumber(),
                    entity.getAddress());
        };
        return convertPage(page, converterFunction);
    }

    public static <E, V> Page<V> convertPage(Page<E> page, Function<E, V> converter) {
        List<V> content = page.getContent()
                .stream()
                .map(converter)
                .collect(Collectors.toList());

        return new PageImpl<>(content, page.getPageable(), page.getTotalElements());
    }

    @Override
    public CustomerDto getCustomer(Long customerId) {
        log.info("get reward summary from CustomerServiceImpl::getCustomer");
        Optional<Customer> customer = customerRepository.findById(customerId);
        CustomerDto customerDto = mapper.fromRewards(customer.get());
        return customerDto;
    }

    @Override
    public CustomerDto insertCustomer(CustomerDto customerDto) {
       Customer customer = customerRepository.save(mapper.toRewards(customerDto));
       return mapper.fromRewards(customer);
    }

    @Override
    public CustomerDto updateCustomer(CustomerDto customerDto) {
        Customer customer = customerRepository.save(mapper.toRewards(customerDto));
        return mapper.fromRewards(customer);
    }

    @Override
    public String deleteCustomer(Long customerId) {
        customerRepository.deleteById(customerId);
        return customerId + " deleted successfully...";
    }
}
