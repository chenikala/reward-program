package com.retailer.reward.program.service;

import com.retailer.reward.program.exception.InsufficientRewardPointsException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static com.retailer.reward.program.util.constants.RewardConstants.INSUFFICIENT_REWARD_POINTS;

@Slf4j
@Component
public class CalculateRewards {

    /**
     * Calculates the reward points based on the transaction amount.
     *
     * @param amount the transaction amount
     * @return the reward points earned
     */
    public int calculateRewardPoints(double amount) {
        log.info("CalculateRewards::calculateRewardPoints");
        log.info("Calculate reward points based on transaction amount.");
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
        log.info("CalculateRewards::redeemRewardPoints");
        if(redeemCount <= rewardPoints)
            return rewardPoints-redeemCount;
        else{
            log.error("In sufficient reward points..");
            throw new InsufficientRewardPointsException(INSUFFICIENT_REWARD_POINTS);
        }
    }
}
