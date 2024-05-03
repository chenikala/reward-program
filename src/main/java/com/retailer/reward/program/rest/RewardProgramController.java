package com.retailer.reward.program.rest;

import com.retailer.reward.program.dto.RewardsDto;
import com.retailer.reward.program.service.RewardProgramService;
import com.retailer.reward.program.util.Api;
import com.retailer.reward.program.util.GenericResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Retailer reward program controller
 */
@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping(Api.Rewards.BASE_URL)
public class RewardProgramController{

    private RewardProgramService rewardProgramService;

    @Operation(summary = "Get list of reward data as JSON",
            description = "Returns the list of reward data in JSON format",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Data retrieved successfully",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = GenericResponse.class)))
            })
    @GetMapping(Api.Rewards.REWARDS_SUMMARY)
    public GenericResponse<List<RewardsDto>> rewardSummary(){
        log.info("RewardProgramController::rewardSummary");
        List<RewardsDto> result = rewardProgramService.rewardSummary();
        return GenericResponse.<List<RewardsDto>>builder()
                .success(true)
                .data(result)
                .build();
    }

    @Operation(summary = "Get reward data as JSON based on customer ID",
            description = "Returns reward data as JSON based on customer ID",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Data retrieved successfully",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = GenericResponse.class)))
            })
    @GetMapping(Api.Rewards.CUSTOMER_REWARDS_SUMMARY)
    public GenericResponse<List<RewardsDto>> customerRewardSummary(@PathVariable(name = "customerId") String customerId){
        log.info("RewardProgramController::customerRewardSummary");
        List<RewardsDto> result = rewardProgramService.customerRewardSummary(customerId);
        return GenericResponse.<List<RewardsDto>>builder()
                .success(true)
                .data(result)
                .build();
    }

    @Operation(summary = "Insert reward data based on transaction amount",
            description = "Returns reward data as JSON",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Data retrieved successfully",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = GenericResponse.class)))
            })
    @PostMapping(Api.Rewards.GET_REWARDS)
    public GenericResponse<RewardsDto> getMyRewards(@RequestBody RewardsDto rewardsDto){
        log.info("RewardProgramController::getMyRewards");
        RewardsDto result = rewardProgramService.getMyRewards(rewardsDto);
        return GenericResponse.<RewardsDto>builder()
                .success(true)
                .data(result)
                .build();
    }
    @Operation(summary = "Redeem reward data based on given redeem points",
            description = "Returns reward data based on given redeem points",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Data retrieved successfully",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = GenericResponse.class)))
            })
    @PutMapping(Api.Rewards.REDEEM_REWARDS)
    public GenericResponse<RewardsDto> redeemMyRewards(@RequestBody RewardsDto rewardsDto){
        log.info("RewardProgramController::redeemMyRewards");
        RewardsDto result = rewardProgramService.redeemMyRewards(rewardsDto);
        return GenericResponse.<RewardsDto>builder()
                .success(true)
                .data(rewardsDto)
                .build();
    }
}
