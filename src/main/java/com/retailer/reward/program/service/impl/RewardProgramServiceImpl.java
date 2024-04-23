package com.retailer.reward.program.service.impl;

import com.retailer.reward.program.dto.RewardsDto;
import com.retailer.reward.program.exception.NotFoundException;
import com.retailer.reward.program.exception.SpentOverEmptyException;
import com.retailer.reward.program.service.CalculateRewards;
import com.retailer.reward.program.service.RewardProgramService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import static com.retailer.reward.program.util.constants.RewardConstants.INVALID_REWARD_DATA;
import static com.retailer.reward.program.util.constants.RewardConstants.SPENTOVER_EMPTY;

/**
 * Reward program service implementation
 */
@Service
@RequiredArgsConstructor
public class RewardProgramServiceImpl implements RewardProgramService {

    private final CalculateRewards calculateRewards;

    /**
     * Get overall reward summary
     * @return
     */
    @Override
    public List<String> rewardSummary() {
        return List.of("test message");
    }

    /**
     * Add rewards to the customer based on spendover
     * @param rewardsDto
     * @return
     */
    @Override
    public List<RewardsDto> getMyRewards(RewardsDto rewardsDto) {
        Optional.of(rewardsDto).orElseThrow(()-> new NotFoundException(INVALID_REWARD_DATA));
        Optional.of(rewardsDto.spentOver()).orElseThrow(()-> new SpentOverEmptyException(SPENTOVER_EMPTY));
        int rewardPoints = calculateRewards.calculateRewardPoints(rewardsDto.spentOver());
        return List.of(new RewardsDto(rewardsDto.customerId(),rewardsDto.customerName(),rewardsDto.spentOver(),rewardPoints,rewardsDto.pastSummary(), Instant.now()));
    }

    /**
     * Redeem existing rewards for existing customer.
     * @param rewardsDto
     * @return
     */
    @Override
    public List<RewardsDto> redeemMyRewards(RewardsDto rewardsDto) {
        return List.of(rewardsDto);
    }
}
