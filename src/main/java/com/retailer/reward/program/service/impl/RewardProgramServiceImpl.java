package com.retailer.reward.program.service.impl;

import com.retailer.reward.program.dto.RewardsDto;
import com.retailer.reward.program.entity.postgres.Rewards;
import com.retailer.reward.program.exception.NotFoundException;
import com.retailer.reward.program.exception.SpentOverEmptyException;
import com.retailer.reward.program.mapper.RewardMapper;
import com.retailer.reward.program.repository.postgres.RewardProgramRepository;
import com.retailer.reward.program.service.CalculateRewards;
import com.retailer.reward.program.service.RewardProgramService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.function.Function;
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
        log.info("get reward summary from RewardProgramServiceImpl::rewardSummary");
        List<Rewards> rewardsList = rewardProgramRepository.findAll();
        return rewardsList.stream().map(rewardMapper::fromRewards).collect(Collectors.toList());
    }

    /**
     * Get overall reward summary
     * @return
     */
    @Override
    public Page<RewardsDto> rewardSummaryPageBy(Pageable pageable) {
        log.info("get reward summary from RewardProgramServiceImpl::rewardSummaryPageBy");
        Page<Rewards> page = rewardProgramRepository.findAll(pageable);
        Function<Rewards, RewardsDto> converterFunction = entity -> {
            return new RewardsDto(String.valueOf(entity.getRewardId()),
                    entity.getCustomerId(),
                    entity.getCustomerName(),
                    entity.getSpentOver(),
                    entity.getRewardPoints(),
                    entity.getTransactionDate(),
                    entity.getRedeemPoints());
        };
        return convertPage(page, converterFunction);
    }

    public static <E, V> Page<V> convertPage(Page<E> page, Function<E, V> converter) {
        List<V> content = page.getContent()
                .stream()
                .map(converter)
                .collect(Collectors.toList());

        return new PageImpl<>(content, page.getPageable(), page.getTotalElements());
    }


    /**
     * Get overall reward summary for one customer
     * @return
     */
    @Override
    public List<RewardsDto> customerRewardSummary(String customerId) {
        log.info("get reward summary from RewardProgramServiceImpl::customerRewardSummary");
        List<Rewards> rewardsList = rewardProgramRepository.findByCustomerId(customerId);
        return rewardsList.stream().map(rewardMapper::fromRewards).collect(Collectors.toList());
    }

    /**
     * Add rewards to the customer based on spendover
     * @param rewardsDto
     * @return
     */
    @Override
    public RewardsDto getMyRewards(RewardsDto rewardsDto) {
        log.info("get all reward list from RewardProgramServiceImpl::getMyRewards");
        if(rewardsDto==null){
            log.error("Rewards data is empty or null");
            throw new NotFoundException(INVALID_REWARD_DATA);
        }
        if(rewardsDto.getSpentOver() <= 0.0){
            log.error("Spent over should be grater than Zero.");
            throw new SpentOverEmptyException(SPENTOVER_EMPTY);
        }
        int rewardPoints = calculateRewards.calculateRewardPoints(rewardsDto.getSpentOver());

        rewardsDto.setRewardPoints(rewardPoints);
        rewardsDto.setTransactionDate(LocalDateTime.now().format(formatter));

        Rewards rewards = rewardMapper.toRewards(rewardsDto);
        Rewards rewardResult = rewardProgramRepository.save(rewards);

        return rewardMapper.fromRewards(rewardResult);
    }

    /**
     * Redeem existing rewards for existing customer.
     * @param rewardsDto
     * @return
     */
    @Override
    public RewardsDto redeemMyRewards(RewardsDto rewardsDto) {
        log.info("RewardProgramServiceImpl::redeemMyRewards");
        List<Rewards> rewardsList = rewardProgramRepository.findByCustomerId(rewardsDto.getCustomerId());
        int finalCount = rewardsList.stream().mapToInt(Rewards::getRewardPoints).sum();
        calculateRewards.redeemRewardPoints(finalCount, rewardsDto.getRedeemPoints());
        return rewardsDto;
    }
}
