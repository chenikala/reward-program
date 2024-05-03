package com.retailer.reward.program.rest.integration;

import com.retailer.reward.program.dto.RewardsDto;
import com.retailer.reward.program.entity.Rewards;
import com.retailer.reward.program.repository.TestRewardProgramRepository;
import com.retailer.reward.program.util.GenericResponse;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RewardProgramControllerTest {

    @LocalServerPort
    private int port;

    private String baseUrl = "http://localhost";

    private static TestRestTemplate restTemplate;

    @Autowired
    private TestRewardProgramRepository repository;

    @BeforeAll
    public static void init(){
        restTemplate = new TestRestTemplate();
    }

    RewardsDto rewards;
    @BeforeEach
    public void setup(){
        rewards = new RewardsDto();
        rewards.setCustomerId("C00002");
        rewards.setCustomerName("test customer");
        rewards.setRewardPoints(333);
        rewards.setSpentOver(21);
        rewards.setRedeemPoints(12);
        baseUrl = baseUrl.concat(":").concat(port+"").concat("/rewardsprogram/api/rewards");
    }

    @Test
    @Sql("/data.sql")
    void getMyRewardsTest(){
        GenericResponse<RewardsDto> response = restTemplate.postForObject(baseUrl+"/getrewards", rewards, GenericResponse.class);
        assertEquals("C00002", ((Map) response.data()).get("customerId"));
    }
    @Test
    @Sql("/data.sql")
    @Sql(statements = "insert into rewards (customer_id, customer_name, redeem_points, reward_points, spent_over, transaction_date, reward_id) values ('C1231', 'test method', 33, 154, 144, '2024-04-27 01:50:16', 1254)", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(statements = "delete from rewards where customer_id='C1231'", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void customerRewardSummaryTest(){
        GenericResponse<RewardsDto> response = restTemplate.getForObject(baseUrl+"/summary/customerId/C1231", GenericResponse.class);
        assertEquals(1, ((List)response.data()).size());
    }
    @Test
    @Sql("/data.sql")
    void rewardsSummaryTest(){
        GenericResponse<RewardsDto> response = RewardProgramControllerTest.restTemplate.getForObject(baseUrl+"/summary", GenericResponse.class);
        assertNotNull(response.data());
        assertEquals(true, response.success());
    }
}
