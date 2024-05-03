package com.retailer.reward.program.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Api {
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Rewards{
        public static final String BASE_URL = "/api/rewards";
        public static final String REWARDS_SUMMARY = "/summary";
        public static final String CUSTOMER_REWARDS_SUMMARY = "/summary/customerId/{customerId}";
        public static final String GET_REWARDS = "/getrewards";
        public static final String REDEEM_REWARDS = "/redeem";
    }
}
