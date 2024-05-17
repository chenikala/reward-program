package com.retailer.reward.program.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
public class CustomerDto {
        private long cid;

        private String name;

        private short age;

        private long phonenumber;

        private String address;
}