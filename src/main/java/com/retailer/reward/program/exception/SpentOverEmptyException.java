package com.retailer.reward.program.exception;

public class SpentOverEmptyException extends RuntimeException{

    public SpentOverEmptyException(String message){
        super(message);
    }

}
