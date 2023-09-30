package com.book.controller;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.book.dto.ErrorResponseDto;
import com.book.exception.UnauthorizedException;
import com.book.exception.UserAlreadyExistException;
import com.book.exception.UserNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
@CrossOrigin
public class GlobalExceptionHandler {

	HttpStatus status;
	
	@ExceptionHandler(UserAlreadyExistException.class)
	public ResponseEntity<ErrorResponseDto> handleUserAlreadyExistException(Exception ex,HttpServletRequest request){
		status = HttpStatus.CONFLICT;
		ErrorResponseDto errorObj = new ErrorResponseDto(LocalDateTime.now(), status.value(), status.getReasonPhrase(), ex.getMessage(), request.getRequestURI());
		return new ResponseEntity<ErrorResponseDto>(errorObj,status);
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ErrorResponseDto> handleUserNotFoundException(Exception ex,HttpServletRequest request){
		status = HttpStatus.NOT_FOUND;
		ErrorResponseDto errorObj = new ErrorResponseDto(LocalDateTime.now(), status.value(), status.getReasonPhrase(), ex.getMessage(), request.getRequestURI());
		return new ResponseEntity<ErrorResponseDto>(errorObj,status);
	}
	
	@ExceptionHandler(UnauthorizedException.class)
	public ResponseEntity<ErrorResponseDto> handleUnauthorizedException(Exception ex,HttpServletRequest request){
		status = HttpStatus.UNAUTHORIZED;
		ErrorResponseDto errorObj = new ErrorResponseDto(LocalDateTime.now(), status.value(), status.getReasonPhrase(), ex.getMessage(), request.getRequestURI());
		return new ResponseEntity<ErrorResponseDto>(errorObj,status);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponseDto> handleOtherException(Exception ex,HttpServletRequest request){
		status = HttpStatus.INTERNAL_SERVER_ERROR;
		ErrorResponseDto errorObj = new ErrorResponseDto(LocalDateTime.now(), status.value(), status.getReasonPhrase(), ex.getMessage(), request.getRequestURI());
		return new ResponseEntity<ErrorResponseDto>(errorObj,status);
	}
	
}
