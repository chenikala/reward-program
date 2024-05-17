package com.retailer.reward.program.service;

import com.retailer.reward.program.dto.RewardsDto;
import com.retailer.reward.program.entity.postgres.Rewards;
import com.retailer.reward.program.exception.NotFoundException;
import com.retailer.reward.program.exception.SpentOverEmptyException;
import com.retailer.reward.program.mapper.RewardMapper;
import com.retailer.reward.program.repository.postgres.RewardProgramRepository;
import com.retailer.reward.program.service.impl.RewardProgramServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RewardProgramServiceImplTest {

    @Mock
    private CalculateRewards calculateRewards;

    @Mock
    private RewardProgramRepository rewardProgramRepository;

    @Mock
    private RewardMapper rewardMapper;

    @InjectMocks
    private RewardProgramServiceImpl rewardProgramServiceImpl;

    @BeforeEach
    public void setUp() {
        // Any setup you might need can go here.
    }

    @Test
    public void testRewardSummary() {
        // Mock the data
        Rewards reward1 = new Rewards();
        reward1.setCustomerId("C0001");
        reward1.setRewardPoints(100);
        Rewards reward2 = new Rewards();
        reward2.setCustomerId("C0002");
        reward2.setRewardPoints(200);

        // Mock the repository method
        when(rewardProgramRepository.findAll()).thenReturn(Arrays.asList(reward1, reward2));

        // Mock the mapping
        when(rewardMapper.fromRewards(any(Rewards.class))).thenReturn(new RewardsDto());

        // Call the method
        List<RewardsDto> rewardsDtos = rewardProgramServiceImpl.rewardSummary();

        // Add assertions as necessary
        assertNotNull(rewardsDtos);
        assertEquals(2, rewardsDtos.size());
    }

    @Test
    public void testGetMyRewards() {
        // Mock the data
        RewardsDto rewardsDto = new RewardsDto();
        rewardsDto.setSpentOver(100.0);
        rewardsDto.setCustomerId("1");
        Rewards rewards = new Rewards();

        // Mock dependencies
        when(calculateRewards.calculateRewardPoints(100.0)).thenReturn(100);
        when(rewardMapper.toRewards(rewardsDto)).thenReturn(rewards);
        when(rewardProgramRepository.save(rewards)).thenReturn(rewards);

        // Mock the mapping
        when(rewardMapper.fromRewards(rewards)).thenReturn(rewardsDto);

        // Call the method
        RewardsDto result = rewardProgramServiceImpl.getMyRewards(rewardsDto);

        // Add assertions as necessary
        assertNotNull(result);
        assertEquals(100, result.getRewardPoints());
    }

    @Test
    public void testGetMyRewardsThrowsNotFoundException() {
        // Test for NotFoundException when rewardsDto is null
        assertThrows(NotFoundException.class, () -> {
            rewardProgramServiceImpl.getMyRewards(null);
        });
    }

    @Test
    public void testGetMyRewardsThrowsSpentOverEmptyException() {
        // Create a RewardsDto instance with spentOver <= 0.0
        RewardsDto rewardsDto = new RewardsDto();
        rewardsDto.setSpentOver(0.0);

        // Test for SpentOverEmptyException when spentOver is <= 0.0
        assertThrows(SpentOverEmptyException.class, () -> {
            rewardProgramServiceImpl.getMyRewards(rewardsDto);
        });
    }

    @Test
    public void testRedeemMyRewards() {
        // Mock the data
        RewardsDto rewardsDto = new RewardsDto();
        rewardsDto.setCustomerId("1");
        rewardsDto.setRedeemPoints(50);

        Rewards reward1 = new Rewards();
        reward1.setRewardPoints(100);

        // Mock dependencies
        when(rewardProgramRepository.findByCustomerId("1")).thenReturn(Arrays.asList(reward1));

        // Call the method
        RewardsDto result = rewardProgramServiceImpl.redeemMyRewards(rewardsDto);

        // Add assertions as necessary
        assertNotNull(result);
        assertEquals(50, rewardsDto.getRedeemPoints());
    }
}
