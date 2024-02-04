package com.microservices.collegeDepartment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.microservices.collegeDepartment.model.ResponseBodyEntity;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler({BusinessException.class})
	public ResponseEntity<ResponseBodyEntity> businessException(BusinessException exception) {
		ResponseBodyEntity responseBodyEntity = new ResponseBodyEntity();
		responseBodyEntity.setResultCode("-1");
		responseBodyEntity.setResultMessage(exception.getMessage());
		return new ResponseEntity<>(responseBodyEntity, HttpStatus.OK);
	}
	
	@ExceptionHandler({RuntimeException.class})
	public ResponseEntity<ResponseBodyEntity> runTimeException(RuntimeException exception) {
		ResponseBodyEntity responseBodyEntity = new ResponseBodyEntity();
		responseBodyEntity.setResultCode("-1");
		responseBodyEntity.setResultMessage(exception.getMessage());
		return new ResponseEntity<>(responseBodyEntity, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler({HttpRequestMethodNotSupportedException.class})
	public ResponseEntity<ResponseBodyEntity> notFoundException(HttpRequestMethodNotSupportedException exception) {
		ResponseBodyEntity responseBodyEntity = new ResponseBodyEntity();
		responseBodyEntity.setResultCode("-1");
		responseBodyEntity.setResultMessage(exception.getMessage());
		return new ResponseEntity<>(responseBodyEntity, HttpStatus.METHOD_NOT_ALLOWED);
	}
	
	@ExceptionHandler({HttpMediaTypeException.class})
	public ResponseEntity<ResponseBodyEntity> methodNotAllowedException(HttpMediaTypeException exception) {
		ResponseBodyEntity responseBodyEntity = new ResponseBodyEntity();
		responseBodyEntity.setResultCode("-1");
		responseBodyEntity.setResultMessage(exception.getMessage());
		return new ResponseEntity<>(responseBodyEntity, HttpStatus.METHOD_NOT_ALLOWED);
	}

}
