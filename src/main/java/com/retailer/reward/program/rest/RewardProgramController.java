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
    public GenericResponse<List<String>> rewardSummary(){
        List<String> result = rewardProgramService.rewardSummary();
        return GenericResponse.<List<String>>builder()
                .success(true)
                .data(result)
                .build();
    }

    @PostMapping(Api.Rewards.GET_REWARDS)
    public GenericResponse<List<RewardsDto>> getMyRewards(@RequestBody RewardsDto rewardsDto){
        List<RewardsDto> result = rewardProgramService.getMyRewards(rewardsDto);
        return GenericResponse.<List<RewardsDto>>builder()
                .success(true)
                .data(result)
                .build();
    }
    @PutMapping(Api.Rewards.REDEEM_REWARDS)
    public GenericResponse<List<RewardsDto>> redeemMyRewards(@RequestBody RewardsDto rewardsDto){
        List<RewardsDto> result = rewardProgramService.redeemMyRewards(rewardsDto);
        return GenericResponse.<List<RewardsDto>>builder()
                .success(true)
                .data(List.of(rewardsDto))
                .build();
    }
}
