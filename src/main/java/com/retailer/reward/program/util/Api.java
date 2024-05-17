package com.retailer.reward.program.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Api {
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Rewards{
        public static final String BASE_URL = "/api/rewards";
        public static final String REWARDS_SUMMARY = "/summary";
        public static final String REWARDS_SUMMARY_PAGE = "/summary/page";
        public static final String CUSTOMER_REWARDS_SUMMARY = "/summary/customerId/{customerId}";
        public static final String GET_REWARDS = "/getrewards";
        public static final String REDEEM_REWARDS = "/redeem";
        public static final String INSERT_CUSTOMER = "/add/customer";
        public static final String UPDATE_CUSTOMER = "/update/customer";
        public static final String DELETE_CUSTOMER = "/delete/customer";
        public static final String GET_CUSTOMER = "/get/customer";
        public static final String GET_ALL_CUSTOMERS = "/get/customers";
        public static final String GET_ALL_CUSTOMERS_PAGE = "/get/customers/page";
    }
}
