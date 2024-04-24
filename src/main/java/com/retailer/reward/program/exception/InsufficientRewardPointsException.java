package com.retailer.reward.program.exception;

public class InsufficientRewardPointsException extends RuntimeException{

    public InsufficientRewardPointsException(String message){
        super(message);
    }

}
