package com.ayush.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiErrorResponse> handleNotFound(ResourceNotFoundException ex){
		ApiErrorResponse error=new ApiErrorResponse(LocalDateTime.now(),
				HttpStatus.NOT_FOUND.value(),
				ex.getMessage());
		return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
	}
	
	  @ExceptionHandler(BadRequestException.class)
	    public ResponseEntity<ApiErrorResponse> handleBadRequest(
	            BadRequestException ex) {

	        ApiErrorResponse error =
	                new ApiErrorResponse(
	                        LocalDateTime.now(),
	                        HttpStatus.BAD_REQUEST.value(),
	                        ex.getMessage());

	        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	    }

	    @ExceptionHandler(Exception.class)
	    public ResponseEntity<ApiErrorResponse> handleGeneric(
	            Exception ex) {

	        ApiErrorResponse error =
	                new ApiErrorResponse(
	                        LocalDateTime.now(),
	                        HttpStatus.INTERNAL_SERVER_ERROR.value(),
	                        "Something went wrong");

	        return new ResponseEntity<>(
	                error,
	                HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	    
	    @ExceptionHandler(MethodArgumentNotValidException.class)
	    public ResponseEntity<Map<String, String>> handleValidation(
	            MethodArgumentNotValidException ex) {

	        Map<String, String> errors = new HashMap<>();

	        ex.getBindingResult()
	                .getFieldErrors()
	                .forEach(error ->
	                        errors.put(
	                                error.getField(),
	                                error.getDefaultMessage()));

	        return new ResponseEntity<>(
	                errors,
	                HttpStatus.BAD_REQUEST);
	    }

}
