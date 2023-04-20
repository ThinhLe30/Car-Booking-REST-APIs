package com.brooklyn.xeke.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class XeKeApiException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	private HttpStatus status;
	private String message;
	public XeKeApiException(HttpStatus status, String message) {
		this.status = status;
		this.message = message;
	}
	public XeKeApiException(HttpStatus status, String message, String message1) {
		super(message);
		this.status = status;
		this.message = message1;
	}
	public HttpStatus getStatus() {
		return status;
	}
	public void setStatus(HttpStatus status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
