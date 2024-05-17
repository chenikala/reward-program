package com.retailer.reward.program.entity.postgres;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Table(name = "rewards")
public class Rewards {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int rewardId;
    private String customerId;
    private String customerName;
    private double spentOver;
    private int rewardPoints;
    private String transactionDate;
    private int redeemPoints;
}
