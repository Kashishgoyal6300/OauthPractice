package com.auth.auth_app.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.auth.auth_app.dto.ErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
	//Resource not found exception handler ::method
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorResponse>handleResorceNotFoundException(ResourceNotFoundException exception){
		ErrorResponse internalServerError=  new ErrorResponse(exception.getMessage(),HttpStatus.NOT_FOUND);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(internalServerError);
	}
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<ErrorResponse>handleIllegalArgumentException(IllegalArgumentException exception){
		ErrorResponse internalServerError=  new ErrorResponse(exception.getMessage(),HttpStatus.BAD_REQUEST);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(internalServerError);
	}
}
