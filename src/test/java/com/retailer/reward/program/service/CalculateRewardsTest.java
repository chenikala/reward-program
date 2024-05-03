package com.retailer.reward.program.service;

import com.retailer.reward.program.exception.InsufficientRewardPointsException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CalculateRewardsTest {

    @InjectMocks
    private CalculateRewards calculateRewards;

    public CalculateRewardsTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRedeemRewardPointsInsufficientPoints() {
        int rewardPoints = 10;
        int redeemCount = 15;

        assertThrows(InsufficientRewardPointsException.class, () -> {
            calculateRewards.redeemRewardPoints(rewardPoints, redeemCount);
        });
    }

    @Test
    public void testRedeemRewardPointsValidRedemption() {
        int rewardPoints = 15;
        int redeemCount = 10;

        int remainingRewardPoints = calculateRewards.redeemRewardPoints(rewardPoints, redeemCount);
        assertEquals(5, remainingRewardPoints);
    }

    @Test
    public void testCalculateRewardPointsAmountLessThan50() {
        double amount = 30;
        int expectedRewardPoints = 0;

        int actualRewardPoints = calculateRewards.calculateRewardPoints(amount);
        assertEquals(expectedRewardPoints, actualRewardPoints);
    }

    @Test
    public void testCalculateRewardPointsAmountBetween50And100() {
        double amount = 75;
        int expectedRewardPoints = 25;

        int actualRewardPoints = calculateRewards.calculateRewardPoints(amount);
        assertEquals(expectedRewardPoints, actualRewardPoints);
    }

    @Test
    public void testCalculateRewardPointsAmountGreaterThan100() {
        double amount = 120;
        int expectedRewardPoints = 90;

        int actualRewardPoints = calculateRewards.calculateRewardPoints(amount);
        assertEquals(expectedRewardPoints, actualRewardPoints);
    }
}
