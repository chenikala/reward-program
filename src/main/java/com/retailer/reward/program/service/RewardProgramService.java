package com.retailer.reward.program.service;

import com.retailer.reward.program.dto.RewardsDto;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * Reward program service interface
 */
public interface RewardProgramService {
    public List<RewardsDto> rewardSummary();
    public RewardsDto getMyRewards(@RequestBody RewardsDto rewardsDto);
    public RewardsDto redeemMyRewards(@RequestBody RewardsDto rewardsDto);
}
