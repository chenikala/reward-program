package com.retailer.reward.program.repository;

import com.retailer.reward.program.entity.Rewards;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RewardProgramRepository extends JpaRepository<Rewards, Long> {
    // Method to find customers by name
    List<Rewards> findByCustomerId(String customerId);
}
