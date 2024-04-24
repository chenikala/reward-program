package com.retailer.reward.program.rest;

import com.retailer.reward.program.dto.RewardsDto;
import com.retailer.reward.program.service.RewardProgramService;
import com.retailer.reward.program.util.Api;
import com.retailer.reward.program.util.GenericResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Retailer reward program controller
 */
@RestController
@AllArgsConstructor
@RequestMapping(Api.Rewards.BASE_URL)
public class RewardProgramController{

    private RewardProgramService rewardProgramService;

    @GetMapping(Api.Rewards.REWARDS_SUMMARY)
    public GenericResponse<List<RewardsDto>> rewardSummary(){
        List<RewardsDto> result = rewardProgramService.rewardSummary();
        return GenericResponse.<List<RewardsDto>>builder()
                .success(true)
                .data(result)
                .build();
    }

    @PostMapping(Api.Rewards.GET_REWARDS)
    public GenericResponse<RewardsDto> getMyRewards(@RequestBody RewardsDto rewardsDto){
        RewardsDto result = rewardProgramService.getMyRewards(rewardsDto);
        return GenericResponse.<RewardsDto>builder()
                .success(true)
                .data(result)
                .build();
    }
    @PutMapping(Api.Rewards.REDEEM_REWARDS)
    public GenericResponse<RewardsDto> redeemMyRewards(@RequestBody RewardsDto rewardsDto){
        RewardsDto result = rewardProgramService.redeemMyRewards(rewardsDto);
        return GenericResponse.<RewardsDto>builder()
                .success(true)
                .data(rewardsDto)
                .build();
    }
}
