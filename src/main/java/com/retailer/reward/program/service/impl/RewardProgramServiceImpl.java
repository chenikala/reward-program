package com.retailer.reward.program.service.impl;

import com.retailer.reward.program.dto.RewardsDto;
import com.retailer.reward.program.entity.Rewards;
import com.retailer.reward.program.exception.NotFoundException;
import com.retailer.reward.program.exception.SpentOverEmptyException;
import com.retailer.reward.program.mapper.RewardMapper;
import com.retailer.reward.program.repository.RewardProgramRepository;
import com.retailer.reward.program.service.CalculateRewards;
import com.retailer.reward.program.service.RewardProgramService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import static com.retailer.reward.program.util.constants.RewardConstants.INVALID_REWARD_DATA;
import static com.retailer.reward.program.util.constants.RewardConstants.SPENTOVER_EMPTY;

/**
 * Reward program service implementation
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class RewardProgramServiceImpl implements RewardProgramService {

    private final CalculateRewards calculateRewards;

    private final RewardProgramRepository rewardProgramRepository;

    private final RewardMapper rewardMapper;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * Get overall reward summary
     * @return
     */
    @Override
    public List<RewardsDto> rewardSummary() {
        log.info("get all reward list");
        List<Rewards> rewardsList = rewardProgramRepository.findAll();
        return rewardsList.stream().map(rewardMapper::fromCountry).collect(Collectors.toList());
    }

    /**
     * Add rewards to the customer based on spendover
     * @param rewardsDto
     * @return
     */
    @Override
    public RewardsDto getMyRewards(RewardsDto rewardsDto) {

        if(rewardsDto==null){
            throw new NotFoundException(INVALID_REWARD_DATA);
        }
        if(rewardsDto.getSpentOver() <= 0.0){
            throw new SpentOverEmptyException(SPENTOVER_EMPTY);
        }
        int rewardPoints = calculateRewards.calculateRewardPoints(rewardsDto.getSpentOver());

        rewardsDto.setRewardPoints(rewardPoints);
        rewardsDto.setTransactionDate(LocalDateTime.now().format(formatter));

        Rewards rewards = rewardMapper.toRewards(rewardsDto);
        Rewards rewardResult = rewardProgramRepository.save(rewards);

        return rewardMapper.fromCountry(rewardResult);
    }

    /**
     * Redeem existing rewards for existing customer.
     * @param rewardsDto
     * @return
     */
    @Override
    public RewardsDto redeemMyRewards(RewardsDto rewardsDto) {
        List<Rewards> rewardsList = rewardProgramRepository.findByCustomerId(rewardsDto.getCustomerId());
        int finalCount = rewardsList.stream().mapToInt(Rewards::getRewardPoints).sum();
        calculateRewards.redeemRewardPoints(finalCount, rewardsDto.getRedeemPoints());
        return rewardsDto;
    }
}
