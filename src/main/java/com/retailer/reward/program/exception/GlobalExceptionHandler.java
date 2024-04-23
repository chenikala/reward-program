package com.retailer.reward.program.exception;

import com.retailer.reward.program.util.GenericResponse;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({SpentOverEmptyException.class})
    public ResponseEntity<GenericResponse<Object>> zeroTransactionAmountException(SpentOverEmptyException exception){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                GenericResponse.builder().success(false).message(exception.getMessage()).data(LocaleContextHolder.getLocale().toString()).build()
        );
    }
    @ExceptionHandler({NotFoundException.class})
    public ResponseEntity<GenericResponse<Object>> invalidRewardDataException(NotFoundException exception){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                GenericResponse.builder().success(false).message(exception.getMessage()).data(LocaleContextHolder.getLocale().toString()).build()
        );
    }
}
