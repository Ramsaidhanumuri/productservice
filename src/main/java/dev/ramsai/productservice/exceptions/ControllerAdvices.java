package dev.ramsai.productservice.exceptions;

import java.time.OffsetDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import dev.ramsai.productservice.dtos.ExceptionDto;

@ControllerAdvice
public class ControllerAdvices {

	@ExceptionHandler(NoDataFoundException.class)
	private ResponseEntity<ExceptionDto> handleNotFoundException(NoDataFoundException notDataFoundException) {
		ExceptionDto response = new ExceptionDto(
				OffsetDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                notDataFoundException.getMessage()
				);
		return new ResponseEntity<ExceptionDto>(response, HttpStatus.NOT_FOUND);
	}
}
