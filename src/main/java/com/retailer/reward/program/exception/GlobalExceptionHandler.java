package com.retailer.reward.program.exception;

import com.retailer.reward.program.util.GenericResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({SpentOverEmptyException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<GenericResponse<Object>> zeroTransactionAmountException(SpentOverEmptyException exception){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                GenericResponse.builder().success(false).message(exception.getMessage()).data(null).build()
        );
    }
    @ExceptionHandler({NotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<GenericResponse<Object>> invalidRewardDataException(NotFoundException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                GenericResponse.builder().success(false).message(exception.getMessage()).data(null).build()
        );
    }

    @ExceptionHandler({InsufficientRewardPointsException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<GenericResponse<Object>> insufficientRewardPointsException(InsufficientRewardPointsException exception){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                GenericResponse.builder().success(false).message(exception.getMessage()).data(null).build()
        );
    }
}
