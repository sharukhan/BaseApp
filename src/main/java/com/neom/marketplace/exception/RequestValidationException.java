package com.neom.marketplace.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class RequestValidationException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	public RequestValidationException() {
		super();
	}
	
	public RequestValidationException(String message) {
		super(message);
	}
	
	public RequestValidationException(Throwable cause) {
		super(cause);
	}
	
	public RequestValidationException(String message, Throwable cause) {
		super(message, cause);
	}

	@Override
	public String getMessage() {
		return super.getMessage();
	}
}
