package com.retailer.reward.program.dto;

import java.time.Instant;

public record RewardsDto(
        String customerId,
        String customerName,
        double spentOver,
        int rewardPoints,
        int pastSummary,
        Instant date) {
}
