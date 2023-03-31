package com.wecare.userservice.exception;

import com.wecare.userservice.dto.ErrorMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ExceptionControllerAdvice {
	@Autowired
	private Environment environment;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorMessage> generalExceptionHandler(Exception ex) {
		 ErrorMessage error = new ErrorMessage();
		 error.setMessage(environment.getProperty(ExceptionConstants.GENERAL_EXCEPTION_MESSAGE.toString()));
	     return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(WecareException.class)
	public ResponseEntity<ErrorMessage> weCareExceptionHandler(WecareException ex) {
		 ErrorMessage error = new ErrorMessage();
//		 logger.info("=====Error Occurred=====");
//		 logger.error("Error", ex);
//		 logger.info(ex.getMessage());
//		 logger.info(environment.toString());
//		 logger.info(environment.getProperty(ex.getMessage()));
		 error.setMessage(environment.getProperty(ex.getMessage()));
	     return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
	
	//Handler for validation failures w.r.t DTOs
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorMessage> handleValidationExceptions(MethodArgumentNotValidException ex) {
		ErrorMessage error = new ErrorMessage();
        error.setMessage(ex.getBindingResult().getAllErrors().stream().map(ObjectError::getDefaultMessage)
	        		.collect(Collectors.joining(", ")));
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
	
	//Handler for validation failures w.r.t URI parameters
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<ErrorMessage> handleConstraintValidationExceptions(
			ConstraintViolationException ex) {
		 ErrorMessage error = new ErrorMessage();
		 error.setMessage(ex.getConstraintViolations().stream().map(ConstraintViolation::getMessage)
	        		.collect(Collectors.joining(", ")));
		 return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
}
