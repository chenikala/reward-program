package com.retailer.reward.program.repository.postgres;

import com.retailer.reward.program.entity.postgres.Rewards;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RewardProgramRepository extends JpaRepository<Rewards, Long> {
    // Method to find customers by name
    List<Rewards> findByCustomerId(String customerId);
}
