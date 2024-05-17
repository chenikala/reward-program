package com.retailer.reward.program.entity.postgres;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "customer")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long cid;

    private String name;

    private short age;

    private long phonenumber;

    private String address;
}
