package com.ecommerce.demo.infrastructure.rest.handler;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.ecommerce.demo.domain.exception.PriceException;
import com.ecommerce.demo.infrastructure.rest.handler.dto.response.ErrorResponseDTO;

@RestControllerAdvice
public class ControllerExceptionHandler {

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ErrorResponseDTO handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
		String msg = String.format("El parámetro [%s] tiene un formato incorrecto -> Valor recibido [%s]", e.getName(), e.getValue());
		
		return ErrorResponseDTO.builder()
				.statusCode(HttpStatus.BAD_REQUEST.value())
				.title(HttpStatus.BAD_REQUEST.name())
				.timestamp(Instant.now())
				.message(msg)
				.build();
	}
	
	@ExceptionHandler(PriceException.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public ErrorResponseDTO handlePriceException(PriceException e) {
		return ErrorResponseDTO.builder()
				.statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
				.title(HttpStatus.INTERNAL_SERVER_ERROR.name())
				.timestamp(Instant.now())
				.message(e.getMessage())
				.build();
	}

}
