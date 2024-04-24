package com.retailer.reward.program.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Rewards {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rewards_SEQ")
    @SequenceGenerator(name = "rewards_SEQ", sequenceName = "rewards_SEQ", allocationSize = 1)
    @Column(name = "reward_id")
    private int rewardId;
    @Column(name = "customer_id")
    private String customerId;
    @Column(name = "customer_name")
    private String customerName;
    @Column(name = "spent_over")
    private double spentOver;
    @Column(name = "reward_points")
    private int rewardPoints;
    @Column(name = "transaction_date")
    private String transactionDate;
    @Column(name = "redeem_points")
    private int redeemPoints;
}
