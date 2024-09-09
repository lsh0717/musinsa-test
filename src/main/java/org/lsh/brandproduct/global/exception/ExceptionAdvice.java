package org.lsh.brandproduct.global.exception;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionAdvice {

	@ExceptionHandler(IllegalArgumentException.class)
	@ResponseBody
	public ResponseEntity<ExceptionRs> handleIllegalArgumentException(IllegalArgumentException exception) {
		ExceptionRs exceptionRs = ExceptionRs.of("Illegal Argument", exception.getMessage());
		return ResponseEntity.badRequest().body(exceptionRs);
	}

	@ExceptionHandler(NoSuchElementException.class)
	@ResponseBody
	public ResponseEntity<ExceptionRs> handleNoSuchElementException(NoSuchElementException exception) {
		ExceptionRs exceptionRs = ExceptionRs.of("No Such Element", exception.getMessage());
		return ResponseEntity.badRequest().body(exceptionRs);
	}

}
