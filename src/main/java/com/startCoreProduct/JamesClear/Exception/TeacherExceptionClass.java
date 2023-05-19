package com.startCoreProduct.JamesClear.Exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
//extends ResponseEntityExceptionHandler   // why do not have to extends this class
@ControllerAdvice
public class TeacherExceptionClass extends ResponseEntityExceptionHandler {

	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ErrorMessage> handleAllExceptions(Exception ex, WebRequest request) throws Exception {
		ErrorMessage errorMessage = new ErrorMessage(LocalDateTime.now(), 
				ex.getMessage(), request.getDescription(false));
		
		errorMessage.setHelloMinh("vo tran nhat minh general Exception");
		
		return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public final ResponseEntity<ErrorMessage> handleUserNotFoundException(Exception ex, WebRequest request) throws Exception {
		ErrorMessage errorMessage = new ErrorMessage(LocalDateTime.now(), 
				ex.getMessage(), request.getDescription(false));
		
		errorMessage.setHelloMinh("vo tran nhat minh UserNotFoundException");
		
		return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.NOT_FOUND);
		
	}

	
	// Bat buoc phai override method handleMethodArgumentNotValid.
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

		ErrorMessage errorMessage = new ErrorMessage(LocalDateTime.now(), 
				ex.getFieldError().getDefaultMessage(), request.getDescription(true));
		
		errorMessage.setHelloMinh("vo tran nhat minh tells invalid PARAMETER");
		
		return new ResponseEntity(errorMessage, HttpStatus.BAD_GATEWAY);
		
	}
	
}
