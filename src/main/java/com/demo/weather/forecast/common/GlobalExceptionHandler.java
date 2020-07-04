package com.demo.weather.forecast.common;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	public static final String DEFAULT_ERROR_VIEW = "error";

	@ExceptionHandler(value = Exception.class)
	protected ResponseEntity<Object> defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
		String message;
	    Throwable cause, resultCause = e;
	    while ((cause = resultCause.getCause()) != null && resultCause != cause) {
	        resultCause = cause;
	    }
	    if (resultCause instanceof ConstraintViolationException) {
	        message = (((ConstraintViolationException) resultCause).getConstraintViolations()).iterator().next().getMessage();
	    } else {
	        resultCause.printStackTrace();
	        message = "Unknown error";
	    }
	    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	            .body(message);
	}

	@ExceptionHandler(value = ServiceException.class)
	public ResponseEntity<Object> handleServiceException(ServiceException se, WebRequest request) {
		String bodyOfResponse = "This should be ServiceException <br> Error Message:" + se.getMessage()
				+ "<br> Error Code:" + se.getErrorCode();
		return handleExceptionInternal(se, bodyOfResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
		// return new ResponseEntity<>(bodyOfResponse, HttpStatus.BAD_REQUEST); we can
		// even throw this in each controller each error which is repetitive
	}

	@ExceptionHandler(value = { IllegalArgumentException.class, IllegalStateException.class })
	protected ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request) {
		String bodyOfResponse = "This should be application specific";
		return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.CONFLICT, request);
	}
}
