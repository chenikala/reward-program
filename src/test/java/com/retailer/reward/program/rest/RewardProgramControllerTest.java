package com.retailer.reward.program.rest;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.Arrays;
import java.util.List;

import com.retailer.reward.program.dto.RewardsDto;
import com.retailer.reward.program.service.RewardProgramService;
import com.retailer.reward.program.util.Api;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(MockitoJUnitRunner.class)
@WebMvcTest(RewardProgramController.class)
public class RewardProgramControllerTest {

    @Mock
    private RewardProgramService rewardProgramService;

    @InjectMocks
    private RewardProgramController rewardProgramController;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(rewardProgramController).build();
    }

    @Test
    public void testRewardSummary() throws Exception {
        // Create a list of RewardsDto for testing
        List<RewardsDto> rewardsList = Arrays.asList(
                new RewardsDto("1", "customer 1", "customer name1", 120.2, 0, "2024-04-24 00:45:46",0),
                new RewardsDto("2", "customer 2", "customer name2", 120.3, 0, "2024-04-25 00:45:46",0)
                );

        // Mock the rewardSummary method of rewardProgramService
        when(rewardProgramService.rewardSummary()).thenReturn(rewardsList);

        // Perform a GET request to the rewardSummary endpoint
        mockMvc.perform(get(Api.Rewards.BASE_URL + Api.Rewards.REWARDS_SUMMARY))
                // Check if the response status is OK (200)
                .andExpect(status().isOk())
                // Check if the response contains the expected JSON data
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.length()").value(2))
                .andExpect(jsonPath("$.data[0].customerId").value("customer 1"))
                .andExpect(jsonPath("$.data[1].customerId").value("customer 2"));
    }

    @Test
    public void testGetMyRewards() throws Exception {
        // Create a sample RewardsDto for testing
        RewardsDto rewardsDto = new RewardsDto();
        rewardsDto.setRewardId("1");
        rewardsDto.setCustomerId("C001");

        // Mock the getMyRewards method of rewardProgramService
//        when(rewardProgramService.getMyRewards(rewardsDto)).thenReturn(rewardsDto);

        // Perform a POST request to the getMyRewards endpoint
        mockMvc.perform(post(Api.Rewards.BASE_URL + Api.Rewards.GET_REWARDS)
                        .contentType("application/json")
                        .content("{\n" +
                                "            \"rewardId\": \"1\",\n" +
                                "            \"customerId\": \"C001\",\n" +
                                "            \"customerName\": \"PrasadR\",\n" +
                                "            \"spentOver\": 120.0,\n" +
                                "            \"rewardPoints\": 90,\n" +
                                "            \"transactionDate\": \"2024-04-24 00:45:46\",\n" +
                                "            \"redeemPoints\": 0\n" +
                                "        }"))
                // Check if the response status is OK (200)
                .andExpect(status().isOk())
                // Check if the response contains the expected JSON data
                .andExpect(jsonPath("$.success").value(true));
//                .andExpect(jsonPath("$.data[0].rewardId").value("1"));
//                .andExpect(jsonPath("$.data.customerId").value("C001"));
    }

    @Test
    public void testRedeemMyRewards() throws Exception {
        RewardsDto rewardsDto = new RewardsDto();
        rewardsDto.setRewardId("1");
        rewardsDto.setCustomerId("C001");

//        when(rewardProgramService.redeemMyRewards(rewardsDto)).thenReturn(rewardsDto);

        mockMvc.perform(put(Api.Rewards.BASE_URL + Api.Rewards.REDEEM_REWARDS)
                        .contentType("application/json")
                        .content("{\n" +
                                "            \"rewardId\": \"1\",\n" +
                                "            \"customerId\": \"C001\",\n" +
                                "            \"customerName\": \"PrasadR\",\n" +
                                "            \"spentOver\": 120.0,\n" +
                                "            \"rewardPoints\": 90,\n" +
                                "            \"transactionDate\": \"2024-04-24 00:45:46\",\n" +
                                "            \"redeemPoints\": 0\n" +
                                "        }"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.rewardId").value("1"))
                .andExpect(jsonPath("$.data.customerId").value("C001"));
    }
}