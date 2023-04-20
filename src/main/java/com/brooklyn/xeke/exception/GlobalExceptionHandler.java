package com.brooklyn.xeke.exception;

import java.net.http.HttpHeaders;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.brooklyn.xeke.payload.ErrorDetail;

@ControllerAdvice
public class GlobalExceptionHandler  extends ResponseEntityExceptionHandler{
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorDetail> handleResourceNotFoundException(ResourceNotFoundException exception,
			WebRequest request) {
		ErrorDetail detail = new ErrorDetail(new Date(), exception.getMessage(), request.getDescription(false));

		return new ResponseEntity<>(detail, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(XeKeApiException.class)
	public ResponseEntity<ErrorDetail> handleBlogApiException(XeKeApiException exception, WebRequest request) {
		ErrorDetail detail = new ErrorDetail(new Date(), exception.getMessage(), request.getDescription(false));

		return new ResponseEntity<>(detail, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDetail> handleGlobalException(Exception exception, WebRequest request) {
		ErrorDetail detail = new ErrorDetail(new Date(), exception.getMessage(), request.getDescription(false));

		return new ResponseEntity<>(detail, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			org.springframework.http.HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach(error -> {
			String field = ((FieldError)error).getField();
			String message = error.getDefaultMessage();
			errors.put(field, message);
		});
		return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	}
	
	
}
