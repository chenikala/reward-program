package com.retailer.reward.program.service;

import org.springframework.stereotype.Component;

@Component
public class CalculateRewards {

    /**
     * Calculates the reward points based on the transaction amount.
     *
     * @param amount the transaction amount
     * @return the reward points earned
     */
    public int calculateRewardPoints(double amount) {
        int rewardPoints = 0;

        if (amount > 100) {
            rewardPoints += (amount - 100) * 2;
            amount = 100;
        }
        if (amount > 50) {
            rewardPoints += (amount - 50);
        }
        return rewardPoints;
    }
}
