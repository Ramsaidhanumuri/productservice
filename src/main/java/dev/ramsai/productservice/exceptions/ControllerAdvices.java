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
	
	@ExceptionHandler(EmptyInputException.class)
	private ResponseEntity<ExceptionDto> handleEmptyInputException(EmptyInputException emptyInputException){
		ExceptionDto responce = new ExceptionDto(
				OffsetDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                emptyInputException.getMessage()
				);
		return new ResponseEntity<ExceptionDto>(responce, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(BadGatewayException.class)
	private ResponseEntity<ExceptionDto> handleBadGatewayException(BadGatewayException badGatewayException){
		ExceptionDto responce = new ExceptionDto(
				OffsetDateTime.now(),
                HttpStatus.BAD_GATEWAY.value(),
                HttpStatus.BAD_GATEWAY.getReasonPhrase(),
                badGatewayException.getMessage()
				);
		return new ResponseEntity<ExceptionDto>(responce, HttpStatus.BAD_REQUEST);
	}
}
