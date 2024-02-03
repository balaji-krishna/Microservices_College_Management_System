package com.microservices.student.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.microservices.student.httpData.StudentResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler({BusinessException.class})
	public ResponseEntity<StudentResponseBody> businessException(BusinessException exception) {
		StudentResponseBody studentResponseBody = new StudentResponseBody();
		studentResponseBody.setResultCode("-1");
		studentResponseBody.setResultMessage(exception.getMessage());
		return new ResponseEntity<>(studentResponseBody, HttpStatus.OK);
	}
	
	@ExceptionHandler({RuntimeException.class})
	public ResponseEntity<StudentResponseBody> runTimeException(RuntimeException exception) {
		StudentResponseBody studentResponseBody = new StudentResponseBody();
		studentResponseBody.setResultCode("-1");
		studentResponseBody.setResultMessage(exception.getMessage());
		return new ResponseEntity<>(studentResponseBody, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler({HttpRequestMethodNotSupportedException.class})
	public ResponseEntity<StudentResponseBody> notFoundException(HttpRequestMethodNotSupportedException exception) {
		StudentResponseBody studentResponseBody = new StudentResponseBody();
		studentResponseBody.setResultCode("-1");
		studentResponseBody.setResultMessage(exception.getMessage());
		return new ResponseEntity<>(studentResponseBody, HttpStatus.METHOD_NOT_ALLOWED);
	}
	
	@ExceptionHandler({HttpMediaTypeException.class})
	public ResponseEntity<StudentResponseBody> methodNotAllowedException(HttpMediaTypeException exception) {
		StudentResponseBody studentResponseBody = new StudentResponseBody();
		studentResponseBody.setResultCode("-1");
		studentResponseBody.setResultMessage(exception.getMessage());
		return new ResponseEntity<>(studentResponseBody, HttpStatus.METHOD_NOT_ALLOWED);
	}

}
