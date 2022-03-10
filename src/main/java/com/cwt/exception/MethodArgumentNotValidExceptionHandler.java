package com.cwt.exception;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class MethodArgumentNotValidExceptionHandler {

	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Error methodArgumentNotValidException(MethodArgumentNotValidException e) {
		BindingResult res = e.getBindingResult();
		Error error = new Error(HttpStatus.BAD_REQUEST.value(),"Error Validation");
		List<FieldError> errField = res.getFieldErrors();
		errField.stream().forEach(field -> error.addFieldError(field.getField(),field.getDefaultMessage())
		);
		
		return error;
	}
	
}
