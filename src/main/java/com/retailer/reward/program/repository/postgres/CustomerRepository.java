package com.retailer.reward.program.repository.postgres;

import com.retailer.reward.program.entity.postgres.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
