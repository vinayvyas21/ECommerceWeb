package com.vk.ecommerce.advices;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.vk.ecommerce.dtos.ExceptionDTO;

@RestControllerAdvice
public class ExceptionsControllerAdvice {

	@ExceptionHandler(ArithmeticException.class)
	public ResponseEntity<ExceptionDTO> handleArithmaticException(ArithmeticException ex) {
		ExceptionDTO dto = new ExceptionDTO();
		dto.setMessage(ex.getMessage());
		return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
	}

}
