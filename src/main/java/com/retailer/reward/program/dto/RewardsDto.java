package com.retailer.reward.program.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
public class RewardsDto {
        private String rewardId;
        private String customerId;
        private String customerName;
        private double spentOver;
        private int rewardPoints;
        private String transactionDate;
        private int redeemPoints;
}