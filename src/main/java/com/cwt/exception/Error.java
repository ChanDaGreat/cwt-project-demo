package com.cwt.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.validation.FieldError;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
public class Error {

	
	
	@NonNull
	private Integer status;
	
	@NonNull
	private String message;
	
	private List<FieldError> error = new ArrayList<>();
	
	public void addFieldError(String field,String message) {
		
		FieldError err = new FieldError(field,message);
		error.add(err);	
	}
	
	@Data
	@AllArgsConstructor
	class FieldError {
		@NonNull
		private String field;
		private String message;
	}
	
	
}
