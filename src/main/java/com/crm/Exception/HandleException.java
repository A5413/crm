package com.crm.Exception;

import com.crm.payload.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice //this becomes like a global class
public class HandleException {
//this class behave as catch block
    @ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<ErrorDetails> handleExceptionEmployee(ResourceNotFound e, WebRequest request){
        ErrorDetails errorDetails = new ErrorDetails(new Date(),
                e.getMessage(),
                request.getDescription(false)
        );
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> globalxceptionEmployee(Exception e, WebRequest request){
        ErrorDetails errorDetails = new ErrorDetails(new Date(),
                e.getMessage(),
                request.getDescription(false)
        );
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }






}
