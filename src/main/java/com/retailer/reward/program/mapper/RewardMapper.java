package com.retailer.reward.program.mapper;

import com.retailer.reward.program.dto.RewardsDto;
import com.retailer.reward.program.entity.Rewards;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RewardMapper {

    Rewards toRewards(RewardsDto rewardsDto);

    RewardsDto fromRewards(Rewards rewards);
}
