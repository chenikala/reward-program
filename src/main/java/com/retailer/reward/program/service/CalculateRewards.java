package com.retailer.reward.program.service;

import com.retailer.reward.program.exception.InsufficientRewardPointsException;
import org.springframework.stereotype.Component;

import static com.retailer.reward.program.util.constants.RewardConstants.INSUFFICIENT_REWARD_POINTS;

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
            rewardPoints += (int) ((amount - 100) * 2);
            amount = 100;
        }
        if (amount > 50) {
            rewardPoints += (int) (amount - 50);
        }
        return rewardPoints;
    }

    /**
     * Redeem existing reward points
     * @param rewardPoints
     * @param redeemCount
     * @return
     */
    public int redeemRewardPoints(int rewardPoints, int redeemCount){
        if(redeemCount <= rewardPoints)
            return rewardPoints-redeemCount;
        else{
            throw new InsufficientRewardPointsException(INSUFFICIENT_REWARD_POINTS);
        }
    }
}
